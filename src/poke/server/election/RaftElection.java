/**
 * 
 */
package poke.server.election;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import poke.core.Mgmt.Management;
import poke.core.Mgmt.LeaderElection.ElectAction;

/**
 * @author nishant
 *
 */
public class RaftElection implements Election {

	protected static Logger logger = LoggerFactory.getLogger("floodmax");

	private Integer nodeId;
	private ElectionState current;
	private int maxHops = -1; // unlimited
	private ElectionListener listener;


	/* (non-Javadoc)
	 * @see poke.server.election.Election#setListener(poke.server.election.ElectionListener)
	 */
	@Override
	public void setListener(ElectionListener listener) {
		this.listener = listener;
	}

	/* (non-Javadoc)
	 * @see poke.server.election.Election#clear()
	 */
	@Override
	public synchronized void clear() {
		current = null;
	}

		

	/* (non-Javadoc)
	 * @see poke.server.election.Election#isElectionInprogress()
	 */
	@Override
	public boolean isElectionInprogress() {
		return current != null;
	}

	/* (non-Javadoc)
	 * @see poke.server.election.Election#getElectionId()
	 */
	@Override
	public Integer getElectionId() {
		if (current == null)
			return null;
		return current.electionID;
	}

	/* (non-Javadoc)
	 * @see poke.server.election.Election#createElectionID()
	 */
	@Override
	public Integer createElectionID() {
		return ElectionIDGenerator.nextID();
	}

	/* (non-Javadoc)
	 * @see poke.server.election.Election#getWinner()
	 */
	@Override
	public Integer getWinner() {
		if (current == null)
			return null;
		else if (current.state.getNumber() == ElectAction.DECLAREELECTION_VALUE)
			return current.candidate;
		else
			return null;
	}

	/* (non-Javadoc)
	 * @see poke.server.election.Election#process(poke.core.Mgmt.Management)
	 */
	@Override
	public Management process(Management req) {
		// TODO Auto-generated method stub
		
		
		
		return null;
	}

	/* (non-Javadoc)
	 * @see poke.server.election.Election#setNodeId(int)
	 */
	@Override
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

}
