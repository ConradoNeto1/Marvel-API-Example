package br.eti.wagnermessias.marvelexample.services;

import br.eti.wagnermessias.marvelexample.entities.Character;

/**
 * Created by Wagner on 05/05/2018.
 */

interface CharactersServiceContract {
    void getCharactersAPI(int countOffset);
    void deleteCharacter(Character character);
}
