package vo;

public class RankVO {
	private String fiveRankCity;
	private int cnt;
	private int per;
	
	public RankVO() {}

	public RankVO(String fiveRankCity, int cnt, int per) {
		super();
		this.fiveRankCity = fiveRankCity;
		this.cnt = cnt;
		this.per = per;
	}

	public String getFiveRankCity() {
		return fiveRankCity;
	}

	public void setFiveRankCity(String fiveRankCity) {
		this.fiveRankCity = fiveRankCity;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getPer() {
		return per;
	}

	public void setPer(int per) {
		this.per = per;
	}

	@Override
	public String toString() {
		return "RankVO [fiveRankCity=" + fiveRankCity + ", cnt=" + cnt + ", per=" + per + "]";
	}


}
