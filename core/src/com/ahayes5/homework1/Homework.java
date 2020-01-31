package com.ahayes5.homework1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Homework extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture p40;
	private Texture bf109;
	private Rectangle player;
	private OrthographicCamera camera;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		p40=new Texture(Gdx.files.internal("p40.png"));
		bf109 = new Texture(Gdx.files.internal("bf109.png"));
		camera= new OrthographicCamera();
		camera.setToOrtho(false,640,1080);

		player=new Rectangle();
		player.width=100;
		player.height = 87;
		player.y=20;
		player.x=camera.viewportWidth/2;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(p40, player.x, player.y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		p40.dispose();
		bf109.dispose();
	}
}
