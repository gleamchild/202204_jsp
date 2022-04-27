package dto;

public class CountryReply {
	private int rnum;
	private String countrycode;
	private String content;
	private int restep;
	private int relevel;
	public CountryReply() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CountryReply(int rnum, String countrycode, String content, int restep, int relevel) {
		super();
		this.rnum = rnum;
		this.countrycode = countrycode;
		this.content = content;
		this.restep = restep;
		this.relevel = relevel;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public String getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRestep() {
		return restep;
	}
	public void setRestep(int restep) {
		this.restep = restep;
	}
	public int getRelevel() {
		return relevel;
	}
	public void setRelevel(int relevel) {
		this.relevel = relevel;
	}
	@Override
	public String toString() {
		return "CountryReply [rnum=" + rnum + ", countrycode=" + countrycode + ", content=" + content + ", restep="
				+ restep + ", relevel=" + relevel + "]";
	}
}
