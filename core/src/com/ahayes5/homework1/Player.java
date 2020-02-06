package com.ahayes5.homework1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Player extends Plane
{
	private float maxX,maxY;
	private long lastBullet= 10000;
	public Player(Texture t, Polygon p, float maxX,float maxY)
	{
		super(t,p);
		this.maxX=maxX;
		this.maxY=maxY;
	}
	public Array<Bullet> move()
	{
		Array<Bullet> bullets=null;
		if(collider!=null)
		{
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
				collider.translate(-175 * Gdx.graphics.getDeltaTime(), 0);
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
				collider.translate(175 * Gdx.graphics.getDeltaTime(), 0);
			if (Gdx.input.isKeyPressed(Input.Keys.UP))
				collider.translate(0, 175 * Gdx.graphics.getDeltaTime());
			if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
				collider.translate(0, -175 * Gdx.graphics.getDeltaTime());
			if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
				bullets = shoot();
		}

		if(collider!=null)
		{
			if (collider.getX() < 0)
				collider.setPosition(0, collider.getY());
			if (collider.getX() + collider.getVertices()[4] > maxX)
				collider.setPosition(maxX - collider.getVertices()[4], collider.getY());
			if (collider.getY() < 0)
				collider.setPosition(collider.getX(), 0);
			if (collider.getY() > maxY - collider.getVertices()[3])
				collider.setPosition(collider.getX(), maxY - collider.getVertices()[3]);
		}
		return bullets;
	}
	private Array<Bullet> shoot()
	{
		Array<Bullet> bullets=new Array<>();
		if(System.currentTimeMillis()-lastBullet>500)
		{
			lastBullet=System.currentTimeMillis();
			Bullet b=new Bullet(7,new Vector2(collider.getTransformedVertices()[4]-45,collider.getTransformedVertices()[3]),new Vector2(0,300),maxX,maxY,true);
			bullets.add(b);
		}
		return bullets;
	}

	@Override
	public void draw(Batch batch)
	{
		batch.draw(texture,collider.getX(),collider.getY(),90,70);
	}

	@Override
	public void explode()
	{

	}
}
