package dto;

public class Covid {
	private int seq;
	private String stateDt;
	private String stateTime;
	private int decideCnt;
	private int dailyDecideCnt;
	private int dailyDeathCnt;
	private	int deathCnt;
	private String createDt;
	private String updateDt;
	public Covid() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Covid(int seq, String stateDt, String stateTime, int decideCnt, int dailyDecideCnt, int dailyDeathCnt,
			int deathCnt, String createDt, String updateDt) {
		super();
		this.seq = seq;
		this.stateDt = stateDt;
		this.stateTime = stateTime;
		this.decideCnt = decideCnt;
		this.dailyDecideCnt = dailyDecideCnt;
		this.dailyDeathCnt = dailyDeathCnt;
		this.deathCnt = deathCnt;
		this.createDt = createDt;
		this.updateDt = updateDt;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getStateDt() {
		return stateDt;
	}
	public void setStateDt(String stateDt) {
		this.stateDt = stateDt;
	}
	public String getStateTime() {
		return stateTime;
	}
	public void setStateTime(String stateTime) {
		this.stateTime = stateTime;
	}
	public int getDecideCnt() {
		return decideCnt;
	}
	public void setDecideCnt(int decideCnt) {
		this.decideCnt = decideCnt;
	}
	public int getDailyDecideCnt() {
		return dailyDecideCnt;
	}
	public void setDailyDecideCnt(int dailyDecideCnt) {
		this.dailyDecideCnt = dailyDecideCnt;
	}
	public int getDailyDeathCnt() {
		return dailyDeathCnt;
	}
	public void setDailyDeathCnt(int dailyDeathCnt) {
		this.dailyDeathCnt = dailyDeathCnt;
	}
	public int getDeathCnt() {
		return deathCnt;
	}
	public void setDeathCnt(int deathCnt) {
		this.deathCnt = deathCnt;
	}
	public String getCreateDt() {
		return createDt;
	}
	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}
	public String getUpdateDt() {
		return updateDt;
	}
	public void setUpdateDt(String updateDt) {
		this.updateDt = updateDt;
	}
	@Override
	public String toString() {
		return "Covid [seq=" + seq + ", stateDt=" + stateDt + ", stateTime=" + stateTime + ", decideCnt=" + decideCnt
				+ ", dailyDecideCnt=" + dailyDecideCnt + ", dailyDeathCnt=" + dailyDeathCnt + ", deathCnt=" + deathCnt
				+ ", createDt=" + createDt + ", updateDt=" + updateDt + "]";
	}
}
