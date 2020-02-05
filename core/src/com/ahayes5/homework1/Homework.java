package com.ahayes5.homework1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Homework extends ApplicationAdapter
{
	SpriteBatch batch;
	Texture p40, bf109;
	OrthographicCamera camera;
	//Sprite player;
	Circle bullet;
	Music propeller;
	Polygon playerPoly;
	ShapeRenderer debug;

	@Override
	public void create()
	{
		camera = new OrthographicCamera();
		camera.setToOrtho(false,500,800);

		Gdx.graphics.setWindowedMode(500,800);

		batch = new SpriteBatch();
		debug = new ShapeRenderer();
		p40 = new Texture(Gdx.files.internal("p40.png"));
		bf109 = new Texture(Gdx.files.internal("bf109.png"));
		propeller = Gdx.audio.newMusic(Gdx.files.internal("propeller.mp3"));

//		playerCenter = new Vector2(Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/2f);
//		player = new Sprite(p40);
//		player.setSize(90,70);
//		player.setPosition(200,20);

		playerPoly=new Polygon();
		System.out.println(playerPoly.getOriginX());
		playerPoly.setOrigin(0,0);
		System.out.println(playerPoly.getOriginX());
		playerPoly.setVertices(new float[]{0,45,45,70,90,45,45,0});


		propeller.setLooping(true);
		propeller.setVolume(.5f);
		propeller.play();
	}

	@Override
	public void render()
	{
		Gdx.gl.glClearColor(.1f, .5f, .1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
			playerPoly.translate(-175*Gdx.graphics.getDeltaTime(),0);
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			playerPoly.translate(175*Gdx.graphics.getDeltaTime(),0);
		if(Gdx.input.isKeyPressed(Input.Keys.UP))
			playerPoly.translate(0,175*Gdx.graphics.getDeltaTime());
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
			playerPoly.translate(0,-175*Gdx.graphics.getDeltaTime());

		if(playerPoly.getX() < 0)
			playerPoly.setPosition(0,playerPoly.getY());
		if(playerPoly.getX()+playerPoly.getVertices()[4] > camera.viewportWidth)
			playerPoly.setPosition(camera.viewportWidth - playerPoly.getVertices()[4],playerPoly.getY());
		if(playerPoly.getY() < 0)
			playerPoly.setPosition(playerPoly.getX(),0);
		if(playerPoly.getY() > camera.viewportHeight - playerPoly.getVertices()[3])
			playerPoly.setPosition(playerPoly.getX(),camera.viewportHeight - playerPoly.getVertices()[3]);

		drawDebug(debug,playerPoly);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(p40,playerPoly.getX(),playerPoly.getY(),90,70);
		//batch.draw(p40,200,20,90,70);
		batch.end();
		camera.update();
	}
	void drawDebug(ShapeRenderer sr,Polygon p)
	{
		sr.begin(ShapeRenderer.ShapeType.Line);
		sr.polygon(p.getTransformedVertices());
		sr.end();
	}

	@Override
	public void dispose()
	{
		batch.dispose();
		p40.dispose();
		bf109.dispose();
	}
}