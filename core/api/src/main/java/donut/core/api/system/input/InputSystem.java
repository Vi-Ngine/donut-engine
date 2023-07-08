package donut.core.api.system.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

import java.util.HashSet;
import java.util.Set;

import donut.core.api.system.EntitySystemWrapable;
import donut.core.api.system.EntitySystemWrapper;
import donut.core.wrapper.ECSystem.EntitiesContainer;
import donut.core.wrapper.ECSystem.EntitySystem;

public class InputSystem extends EntitySystem {

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
