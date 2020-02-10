package com.ahayes5.homework1;

import com.badlogic.gdx.utils.Array;

public class Level
{
	private Array<Formation> formations;
	private Array<Long> delays;
	public Level()
	{
		formations = new Array<>();
		delays = new Array<>();
	}
	public void addFormation(Formation f,long l)
	{
		formations.add(f);
		delays.add(l);
	}
	public Formation removeFormation(int i)
	{
		delays.removeIndex(i);
		return formations.removeIndex(i);
	}
}
