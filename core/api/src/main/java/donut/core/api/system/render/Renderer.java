package donut.core.api.system.render;

import donut.core.api.component.ComponentWrapable;
import donut.core.api.component.ComponentWrapper;

import com.badlogic.ashley.core.ComponentMapper;
import donut.core.wrapper.ECSystem.Component;

import java.util.HashSet;
import java.util.Set;

public class Renderer extends Component {
    private final Set<IRenderCallback> renderLiseners = new HashSet<>();

    public void addRenderCallback(IRenderCallback renderCallback)
    {
        renderLiseners.add(renderCallback);
    }

    public IRenderCallback[] getRenderCallBacks()
    {
        renderLiseners.remove(null);
        return renderLiseners.toArray(new IRenderCallback[0]);
    }
}
