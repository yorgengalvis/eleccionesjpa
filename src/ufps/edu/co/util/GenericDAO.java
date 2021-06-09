package ufps.edu.co.util;

import java.util.List;

public interface GenericDAO<T> {

	public List<T> list();
	public  <E> T find(E id);
	public void insert(T obj);
	public void update(T obj);
	public void delete(T obj);
}
