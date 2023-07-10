/*
package donut.core.api.system.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import donut.core.api.GameSystem;

import java.util.HashSet;
import java.util.Set;

public class InputSystem extends GameSystem {

    Set<InputProcessor> inputProcessors = new HashSet<>();

    public InputSystem()
    {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                for(InputProcessor inputProcessor : getInputProcessors())
                {
                    if(inputProcessor.keyDown(keycode)) return true;
                }

                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                for(InputProcessor inputProcessor : getInputProcessors())
                {
                    if(inputProcessor.keyUp(keycode)) return true;
                }

                return false;
            }
        });
    }

    public void addInputProcessor(InputProcessor inputProcessor)
    {
        inputProcessors.add(inputProcessor);
    }

    public InputProcessor[] getInputProcessors()
    {
        inputProcessors.remove(null);
        return inputProcessors.toArray(new InputProcessor[0]);
    }
}
*/
