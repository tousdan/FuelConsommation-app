package com.tousdan.tasks;

public abstract class AsyncTaskResult<V, E> {
	
	public abstract boolean isError();
	public abstract V getValue();
	public abstract E getError();

}
