package pobj.pinboard.editor;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.commands.Command;
import pobj.pinboard.editor.commands.CommandAdd;
import pobj.pinboard.editor.commands.CommandGroup;
import pobj.pinboard.editor.commands.CommandUngroup;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolRect;
import pobj.pinboard.editor.tools.ToolSelection;

public class EditorWindow implements EditorInterface,ClipboardListener {
	private Stage stage;
	private Board board;
	
	private Tool tool;
	private Selection selection;
	private Clipboard clipboard;
	private Menu edit;
	
	private Canvas canvas;
	
	private CommandStack commandStack;

	public EditorWindow(Stage s) {
		stage=s;
		board=new Board();
		tool=new ToolRect();
		
		
		selection=new Selection();
		commandStack = new CommandStack();
		
		stage.setTitle("New File");
		
		//MenuBar
		MenuItem miNew=new MenuItem("New");
		MenuItem miClose=new MenuItem("Close");
		Menu file=new Menu("File");
		file.getItems().addAll(miNew,miClose);
		MenuItem miCopy=new MenuItem("Copy");
		MenuItem miPaste=new MenuItem("Paste");
		MenuItem miDelete=new MenuItem("Delete");
		MenuItem miGroup=new MenuItem("Group");
		MenuItem miUngroup=new MenuItem("Ungroup");
		MenuItem miUndo=new MenuItem("Undo");
		MenuItem miRedo=new MenuItem("Redo");
		miUndo.setDisable(false);
		miRedo.setDisable(false);
		
		edit=new Menu("Edit");
		edit.getItems().addAll(miCopy,miPaste,miDelete,miGroup,miUngroup,miUndo,miRedo);
		MenuItem miRect=new MenuItem("Rectangle");
		MenuItem miEllipse=new MenuItem("Ellipse");
		Menu tools=new Menu("Tools");
		tools.getItems().addAll(miRect,miEllipse);
		MenuBar menubar=new MenuBar(file,edit,tools);
		
		//ToolBar
		Button btnBox=new Button("Box");
		Button btnEllipse=new Button("Ellipse");
		Button btnImg=new Button("Img...");
		Button btnSelect=new Button("Select");
		ToolBar toolbar=new ToolBar(btnBox,btnEllipse,btnImg,btnSelect);
		
		//Canvas
		canvas=new Canvas(800,600);
		//canvas.getGraphicsContext2D().setStroke(Color.BLACK);
		//canvas.getGraphicsContext2D().setLineWidth(5);
		//Separator
		Separator separator=new Separator();
		//Label
		Label lblStatut=new Label("Filled rectangle tool");
		//Layout
		VBox vbox=new VBox();
		vbox.getChildren().addAll(menubar,toolbar,canvas,separator,lblStatut);
		
		//Actions
		clipboard=Clipboard.getInstance();
		clipboard.addListener(this);
		miNew.setOnAction(
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						new EditorWindow(new Stage());
					}
				}
				);
		miClose.setOnAction((e)->{
			stage.close();
			clipboard.removeListener(this);
		});
		miRect.setOnAction((e)->{
			tool=new ToolRect();
			lblStatut.setText(tool.getName(this));
		});
		miEllipse.setOnAction((e)->{
			tool=new ToolEllipse();
			lblStatut.setText(tool.getName(this));
		});
		miCopy.setOnAction((e)->{
			clipboard.copyToClipboard(selection.getContents());
		});
		miPaste.setOnAction((e)->{
			Command cmdAdd = new CommandAdd(this, clipboard.copyFromClipboard());
			cmdAdd.execute();
			commandStack.addCommand(cmdAdd);
			//board.addClip(clipboard.copyFromClipboard());
			undoChanged();
			redoChanged();
			board.draw(canvas.getGraphicsContext2D());
		});
		miDelete.setOnAction((e)->{
			board.removeClip(selection.getContents());
			board.draw(canvas.getGraphicsContext2D());
		});
		
		miGroup.setOnAction((e)->{
			System.out.println("in size="+board.getContents().size());
			Command cmdGroup = new CommandGroup(this, selection.getContents());
			cmdGroup.execute();
			commandStack.addCommand(cmdGroup);
			//			for(Clip c:selection.getContents()) {
//				board.removeClip(c);
//			}
			System.out.println("remove size="+board.getContents().size());
			//ClipGroup cg=new ClipGroup(selection.getContents());
			//board.addClip(cg);
			System.out.println("add size="+board.getContents().size());
			undoChanged();
			redoChanged();
			board.draw(canvas.getGraphicsContext2D());
		});
		miUngroup.setOnAction((e)->{
			for(Clip clip:selection.getContents()) {
				if(clip instanceof ClipGroup) {
					Command cmdUngroup =new CommandUngroup(this, (ClipGroup)clip);
					cmdUngroup.execute();
					commandStack.addCommand(cmdUngroup);
				}
			}
			undoChanged();
			redoChanged();
			board.draw(canvas.getGraphicsContext2D());
		});
		
		
		miUndo.setOnAction((e)->{
			commandStack.undo();
			
			undoChanged();
			redoChanged();
			board.draw(canvas.getGraphicsContext2D());
		});
		
		miRedo.setOnAction((e)->{
			commandStack.redo();
			
			undoChanged();
			redoChanged();
			board.draw(canvas.getGraphicsContext2D());
		});
		
		btnBox.setOnAction((e)->{
			tool=new ToolRect();
			lblStatut.setText(tool.getName(this));
		});
		btnEllipse.setOnAction((e)->{
			tool=new ToolEllipse();
			lblStatut.setText(tool.getName(this));
		});
		btnSelect.setOnAction((e)->{
			tool=new ToolSelection();
			lblStatut.setText(tool.getName(this));
		});
		canvas.setOnMousePressed((e)->{
			//canvas.getGraphicsContext2D().beginPath();
			tool.press(this,e);});
		canvas.setOnMouseDragged((e)->{
				tool.drag(this,e);
				draw();
			});
		canvas.setOnMouseReleased((e)->{
			tool.release(this,e);
			//canvas.getGraphicsContext2D().closePath();
			//draw();
			undoChanged();
			redoChanged();
			board.draw(canvas.getGraphicsContext2D());
			});
		
		stage.setScene(new Scene(vbox));		
		/*board.addClip(new ClipRect(100, 100, 300, 200, Color.BLUE));
		board.addClip(new ClipEllipse(200, 150, 400, 250, Color.RED));
		board.draw(canvas.getGraphicsContext2D());*/
		stage.show();
	}

	public Board getBoard() {
		return board;
	}
	
	public Selection getSelection() {
		return selection;
	}
	
	public CommandStack getUndoStack() {
		return commandStack;
	}
	
	public void draw() {
		tool.drawFeedback(this, canvas.getGraphicsContext2D());
		//board.draw(canvas.getGraphicsContext2D());
	}
	
	public void clipboardChanged() {
		if(clipboard.isEmpty())
			edit.getItems().get(1).setDisable(true);
		else
			edit.getItems().get(1).setDisable(false);
	}
	
	public void undoChanged() {
		if(commandStack.isUndoEmpty()) 
			edit.getItems().get(5).setDisable(true);
		else
			edit.getItems().get(5).setDisable(false);
	}
	
	public void redoChanged() {
		if(commandStack.isRedoEmpty()) 
			edit.getItems().get(6).setDisable(true);
		else
			edit.getItems().get(6).setDisable(false);
	}
}
