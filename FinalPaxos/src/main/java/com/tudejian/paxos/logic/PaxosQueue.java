package com.tudejian.paxos.logic;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import com.google.common.collect.Lists;
import com.tudejian.paxos.logic.PaxosListeners.QueueUpdateListener;

public final class PaxosQueue<E>
{
	public PaxosQueue(Queue<E> queue)
	{
		m_queue = queue;
		m_listeners = Lists.newCopyOnWriteArrayList();
	}

	public boolean add(E object)
	{
		boolean added = m_queue.add(object);
		if (added)
			queueUpdated();

		return added;
	}

	public void clear()
	{
		m_queue.clear();
		queueUpdated();
	}

	public boolean isEmpty()
	{
		return m_queue.isEmpty();
	}

	public E poll()
	{
		E e = m_queue.poll();
		queueUpdated();
		return e;
	}

	public E peek()
	{
		return m_queue.peek();
	}

	public Iterator<E> iterator()
	{
		return m_queue.iterator();
	}

	public void addQueueUpdateListener(QueueUpdateListener listener)
	{
		m_listeners.add(listener);
		queueUpdated();
	}

	public void removeQueueUpdateListener(QueueUpdateListener listener)
	{
		m_listeners.remove(listener);
	}

	private void queueUpdated()
	{
		StringBuilder builder = new StringBuilder();
		Iterator<E> it = m_queue.iterator();
		while (it.hasNext())
		{
			builder.append(it.next().toString());
			builder.append('\n');
		}
		String queueContents = builder.toString();

		for (QueueUpdateListener listener : m_listeners)
			listener.onQueueUpdate(queueContents);		
	}

	private final Queue<E> m_queue;
	private final List<QueueUpdateListener> m_listeners;
}
