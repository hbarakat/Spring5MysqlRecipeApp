package ma.aqary.recipeapp.converters;

import ma.aqary.recipeapp.commands.NotesCommand;
import ma.aqary.recipeapp.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Hamid Barakat
 */
class NotesCommandToNotesTest {

    public static final Long ID_VALUE = Long.valueOf(1L);
    public static final String NOTES = "description";
    NotesCommandToNotes conveter;

    @BeforeEach
    void setUp() {
        conveter = new NotesCommandToNotes();
    }

    @Test
    void nullParameterTest(){
        assertNull(conveter.convert(null));
    }

    @Test
    void emptyParameterTest(){
        assertNotNull(conveter.convert(new NotesCommand()));
    }


    @Test
    void convert() {
        NotesCommand notesCommand =new NotesCommand();
        notesCommand.setId(ID_VALUE);
        notesCommand.setRecipeNotes(NOTES);
        Notes notes = conveter.convert(notesCommand);
        assertNotNull(notes);
        assertEquals(ID_VALUE,notes.getId());
        assertEquals(NOTES,notes.getNotes());
    }
}