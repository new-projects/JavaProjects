package com.tudejian.paxos.logic;

public enum CommandMessage
{
	TOGGLE_PAUSE_STATE,
	ENTER,
	LEAVE,
	LEAVE_WITH_AMNESIA,
	RESIGN;
}
