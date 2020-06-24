package ma.aqary.recipeapp.services;

import ma.aqary.recipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * @author Hamid Barakat
 */
public interface UnitOfMeasureService {
     Set<UnitOfMeasureCommand> listAllUoms();

    }
