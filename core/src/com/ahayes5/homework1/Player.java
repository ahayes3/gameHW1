package com.ahayes5.homework1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Player extends Plane
{
	private float maxX,maxY;
	private long lastBullet= 10000;
	private long respawn;
	private long timeMilli;
	private ParticleEffect myExplosion;

	public Player(Texture t, Polygon p, float maxX,float maxY)
	{
		super(t,p);
		this.maxX=maxX;
		this.maxY=maxY;
		respawn = -1;
		timeMilli = System.currentTimeMillis();
		myExplosion= new ParticleEffect(Homework.explosion);
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
		if(!exploded)
		{
			Array<Bullet> bullets = new Array<>();
			if (System.currentTimeMillis() - lastBullet > 500)
			{
				lastBullet = System.currentTimeMillis();
				Bullet b = new Bullet(7, new Vector2(collider.getTransformedVertices()[4] - 45, collider.getTransformedVertices()[3]), new Vector2(0, 300), maxX, maxY, true);
				bullets.add(b);
			}
			return bullets;
		}
		return null;
	}
	public boolean checkCollision(Plane p)
	{
		if(Intersector.overlapConvexPolygons(this.collider,p.collider) && timeMilli - respawn>3000 && !exploded)
			return true;
		return false;
	}
	public boolean checkCollision(Bullet b)
	{
		if(Intersector.overlapConvexPolygons(this.collider,b.collider) && !b.fromPlayer() && timeMilli - respawn>3000 && !exploded)
			return true;
		return false;
	}

	@Override
	public void draw(Batch batch)
	{
		if(!exploded)
			batch.draw(texture,collider.getX(),collider.getY(),90,70);
		else
			myExplosion.draw(batch,Gdx.graphics.getDeltaTime());
	}

	@Override
	public void explode()
	{
		exploded = true;
		myExplosion.setPosition(collider.getX()+collider.getBoundingRectangle().width/2, collider.getY() + collider.getBoundingRectangle().height/2);
		myExplosion.start();

	}
}
