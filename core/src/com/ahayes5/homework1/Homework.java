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
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class Homework extends ApplicationAdapter
{
	SpriteBatch batch;
	Texture p40, bf109;
	public static OrthographicCamera camera;
	Music propeller;
	Polygon playerPoly,bf109poly;
	ShapeRenderer debug;
	Player player;
	Array<Bullet> bullets;
	EnemyTemplate enemy;
	Array<Enemy> enemies;

	@Override
	public void create()
	{
		bullets=new Array<Bullet>();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,500,800);

		Gdx.graphics.setWindowedMode(500,800);

		batch = new SpriteBatch();
		debug = new ShapeRenderer();
		p40 = new Texture(Gdx.files.internal("p40.png"));
		bf109 = new Texture(Gdx.files.internal("bf109.png"));
		propeller = Gdx.audio.newMusic(Gdx.files.internal("propeller.mp3"));

		playerPoly=new Polygon();
		playerPoly.setOrigin(0,0);
		playerPoly.setVertices(new float[]{0,45,45,70,90,45,45,0});

		player = new Player(p40,playerPoly,camera.viewportWidth,camera.viewportHeight);
		propeller.setLooping(true);
		propeller.setVolume(.5f);
		propeller.play();

		bf109poly=new Polygon();
		bf109poly.setOrigin(0,0);
		bf109poly.setVertices(new float[]{0,45,40,60,80,45,40,0});
		enemy = new EnemyTemplate(bf109,bf109poly,1,100,
				new Vector2(camera.viewportWidth,camera.viewportHeight),100
				,new Vector2(80,60));
		enemies=new Array<>();
		Formation formation = new Formation(7,enemy);
		enemies.addAll(formation.generateFormation(2));
	}

	@Override
	public void render()
	{
		Gdx.gl.glClearColor(.1f, .5f, .1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Array<Bullet> temp= player.move();
		if(temp!=null)
			bullets.addAll(temp);

		for(Iterator<Bullet> itr = bullets.iterator();itr.hasNext();)
		{
			Bullet b = itr.next();
			if(b.offScreen)
				itr.remove();
			else
				b.move();
		}
		for(Iterator<Enemy> itr = enemies.iterator();itr.hasNext();)
		{
			Enemy e = itr.next();
			if(e.getPosition().y<0)
				itr.remove();
			else
				e.move();
		}
		drawDebug(debug,playerPoly);
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		player.draw(batch);
		for(Bullet b:bullets)
		{
			if(b!=null)
				b.draw(batch);
		}
		for(Enemy e: enemies)
		{
			if(e!=null)
				e.draw(batch);
		}
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
		Bullet.texture.dispose();
		batch.dispose();
		p40.dispose();
		bf109.dispose();
	}
}