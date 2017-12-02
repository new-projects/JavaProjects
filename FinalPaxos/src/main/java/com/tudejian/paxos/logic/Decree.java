package com.tudejian.paxos.logic;

import com.google.common.base.Preconditions;

public class Decree
{
	public static final String DELIMITER = " | ";
	public static final int NO_ID = -1;

	private Decree(int id, DecreeType decreeType, String value, int interval)
	{
		m_id = id;
		m_decreeType = decreeType;
		m_value = value;
		m_interval = interval;
	}

	public static Decree createAddParticipantDecree(int decreeId, int participantId)
	{
		return new Decree(decreeId, DecreeType.ADD_PARTICIPANT, Integer.toString(participantId), NO_INTERVAL);
	}

	public static Decree createRemoveParticipantDecree(int decreeId, int participantId)
	{
		return new Decree(decreeId, DecreeType.REMOVE_PARTICIPANT, Integer.toString(participantId), NO_INTERVAL);
	}

	public static Decree createSetLeaderDecree(int decreeId, int leaderId, int interval)
	{
		return new Decree(decreeId, DecreeType.SET_LEADER, Integer.toString(leaderId), interval);
	}

	public static Decree createOpaqueDecree(int decreeId, String value)
	{
		return new Decree(decreeId, DecreeType.OPAQUE_DECREE, value, NO_INTERVAL);
	}

	public int getDecreeId()
	{
		return m_id;
	}

	public DecreeType getDecreeType()
	{
		return m_decreeType;
	}

	public String getDecreeValue()
	{
		return m_value;
	}

	public int getLeaderExpiry()
	{
		Preconditions.checkState(m_interval != NO_INTERVAL);

		return m_interval;
	}

	public static Decree fromString(String decreeString)
	{
		String[] fields = decreeString.split(DELIMITER_REGEX);
		
		Preconditions.checkState(fields.length == 4);
		
		int interval = fields[3].equals("NO_INTERVAL") ? NO_INTERVAL : Integer.parseInt(fields[3]);
		return new Decree(Integer.parseInt(fields[0]), DecreeType.valueOf(fields[1]), fields[2],
				interval);
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder(Integer.toString(m_id));
		builder.append(DELIMITER);
		builder.append(m_decreeType.toString());
		builder.append(DELIMITER);
		builder.append(m_value);
		builder.append(DELIMITER);
		if (m_interval == NO_INTERVAL)
			builder.append("NO_INTERVAL");
		else
			builder.append(m_interval);
		
		return builder.toString();
	}

	private static final int NO_INTERVAL = -1;
	private static final String DELIMITER_REGEX = " \\| ";

	private final int m_id;
	private final DecreeType m_decreeType;
	private final String m_value;
	private final int m_interval;
}
