package com.tousdan.tasks;

public class AsyncTaskSuccess<V, E> extends AsyncTaskResult<V, E> {

	private V value;
	
	public AsyncTaskSuccess(V value) {
		this.value = value;
	}

	@Override public boolean isError() { return false; }
	@Override public V getValue() { return value; }
	@Override public E getError() { return null; }

}
