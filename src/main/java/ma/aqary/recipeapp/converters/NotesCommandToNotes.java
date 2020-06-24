package ma.aqary.recipeapp.converters;

import lombok.Synchronized;
import ma.aqary.recipeapp.commands.NotesCommand;
import ma.aqary.recipeapp.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Hamid Barakat
 */
@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand source) {
        if(source == null) {
            return null;
        }

        final Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setNotes(source.getRecipeNotes());
        return notes;
    }
}
