package ma.aqary.recipeapp.converters;

import lombok.Synchronized;
import ma.aqary.recipeapp.commands.UnitOfMeasureCommand;
import ma.aqary.recipeapp.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Hamid Barakat
 */
@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {
        @Nullable
        @Synchronized
        @Override
        public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
            if (unitOfMeasure==null){
                return null;
            }
            final UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
            uomCommand.setId(unitOfMeasure.getId());
            uomCommand.setDescription(unitOfMeasure.getDescription());
            return  uomCommand;
        }
    }
