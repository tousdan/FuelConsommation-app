package com.tousdan.tasks;

public class AsyncTaskError<V, E> extends AsyncTaskResult<V, E> {
	private E error;
	
	public AsyncTaskError(E error) {
		this.error = error;
	}
	
	@Override public boolean isError() { return true; }
	@Override public V getValue() { return null; }
	@Override public E getError() { return error; }
}
