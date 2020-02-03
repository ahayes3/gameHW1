package com.ahayes5.homework1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Homework extends ApplicationAdapter
{
	SpriteBatch batch;
	Texture p40, bf109;
	OrthographicCamera camera;
	Sprite player;

	@Override
	public void create()
	{
		batch = new SpriteBatch();
		p40 = new Texture(Gdx.files.internal("p40.png"));
		bf109 = new Texture(Gdx.files.internal("bf109.png"));
		camera = new OrthographicCamera();
		camera.setToOrtho(false,600,1000);
		player = new Sprite(p40);
		player.setSize(100,83);
		player.setPosition(265,20);

	}

	@Override
	public void render()
	{
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(player,270,20,100,83);
		batch.end();
		camera.update();
	}

	@Override
	public void dispose()
	{
		batch.dispose();
	}
}
