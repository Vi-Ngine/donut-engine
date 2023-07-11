package donut.core.api.system.debug;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import donut.core.api.GameComponent;
import donut.core.api.GameEntity;
import donut.core.api.system.render.IRenderCallback;
import donut.core.wrapper.ECSystem.Component;
import donut.core.wrapper.RESquest.IConsumeCallback;

public class Debugger extends GameComponent implements IRenderCallback, IConsumeCallback {
    private boolean hasRenderer = false;
    GameEntity gameEntity;

    public Debugger()
    {
        requestResource(GameEntity.class, Debugger.class, this);
    }

    public boolean hasRenderer()
    {
        return hasRenderer;
    }

    @Override
    public void render(float deltaTime, SpriteBatch batch, ShapeRenderer shapeRenderer)
    {
        hasRenderer = true;

        BitmapFont font = new BitmapFont();
        batch.begin();
        font.draw(batch, debugText, gameEntity.transform.px, gameEntity.transform.py);
        batch.end();
    }

    public String debugText = "Debug me!";

    @Override
    public boolean consume(Object resource) {
        if(resource instanceof GameEntity)
        {
            this.gameEntity = (GameEntity) resource;
            return true;
        }
        return false;
    }
}
