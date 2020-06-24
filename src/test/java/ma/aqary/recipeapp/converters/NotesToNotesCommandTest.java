package ma.aqary.recipeapp.converters;

import ma.aqary.recipeapp.commands.NotesCommand;
import ma.aqary.recipeapp.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Hamid Barakat
 */
class NotesToNotesCommandTest {

    public static final Long ID_VALUE = Long.valueOf(1L);
    public static final String NOTES = "description";
    NotesToNotesCommand conveter;

    @BeforeEach
    void setUp() {
        conveter=new NotesToNotesCommand();
    }
    @Test
    void isNullTest(){
        assertNull(null);
    }
    @Test
    void isEmptyTest(){
        assertNotNull(new Notes());
    }

    @Test
    void convert() {
        Notes notes=new Notes();
        notes.setId(ID_VALUE);
        notes.setNotes(NOTES);
        NotesCommand notesCommand= conveter.convert(notes);
        assertNotNull(notesCommand);
        assertEquals(ID_VALUE,notesCommand.getId());
        assertEquals(NOTES,notesCommand.getRecipeNotes());

    }
}