package com.sohu.mrd.readkafka;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




/**
 * @author chencheng
 * @date Oct 17, 2013 7:17:36 PM
 **/
public class MrdNewsObject_Kafka implements Serializable {

	private static final Log LOG = LogFactory.getLog(MrdNewsObject_Kafka.class);

	private static final long serialVersionUID = -6724119019694326929L;

	private String nid = ""; // 新闻ID
	private String oid = "-1"; // CMS新闻ID
	private String shousoid = "-1"; // shouso新闻ID
	private String ds = ""; // 新闻源URL
	private long ct; // 新闻首次发布创建时间
	private long oct; // 同类新闻最早首次发布创建时间
	private String t = ""; // 新闻标题
	private String su = ""; // 新闻摘要
	private String co = ""; // 新闻内容
	private String me = ""; // 新闻发布媒体，新闻最初来源  --修改为何subname一致
	private int[] ch = new int[] { -1 }; // 新闻频道
	private NewsKeyWordResult[] kw = new NewsKeyWordResult[0]; // 新闻关键字
	private NewsKeyWordResult[] kwold = new NewsKeyWordResult[0]; // 新闻旧关键字
	private NewsKeyWordResultOriginal[] kworiginal = new NewsKeyWordResultOriginal[0]; // 新闻原始关键字//20160225
	
	private NewsGroupResult[] g = new NewsGroupResult[0]; // 新闻组
	private NewsGroupResult2[] g2 = new NewsGroupResult2[0]; 		//A系统back 新闻组2
	private NewsGroupResult3[] g3 = new NewsGroupResult3[0]; 		//A系统back 新闻组2
	private NewsPicResult[] pic = new NewsPicResult[0]; // 新闻图片
	private NewsVideoResult[] video = new NewsVideoResult[0]; // 新闻视频
	private String[] radio = new String[0];
	private String cl = ""; // 新闻类别
	private String sub_cl = ""; // 新闻子类别
	private String lz = ""; // 新闻归属地
	private double loc_score = 0.0; // 地域得分
	private int rank = -1; // 新闻rank值
	private String es = "r"; // 编辑干预状态
	private String chan = ""; // 新闻频道类别，仅做参考用
	private String newsfrom = ""; // 新闻爬取源
	private int status = 0; // 默认0为待推， 1为不推
	private int reason = 0; //
	private String bread = ""; // 面包屑
	private String pageType = "";
	private String pageRank = "";
	private String newsType = "";
	private String siteRank = ""; // 站点rank
	private double mediarank = 0.0;
	private int idtype = -1; // 0:cmsid, 1: md5(url)
	private int st = 1; // st： 资讯类型 （ 1 新闻 2 组图 3 视频 4 直播 5 专题 6 快讯 7 流刊物 8 期刊 9
						// 推广 10 微闻 11 天气）
	private long createTime = -1L; // 新闻创建时间
	private String remark = ""; // 备注信息
	private String subName = ""; // 媒体子名称
	private String subType = ""; // 媒体类型
	private String site = ""; // 来源站点
	private int copyright = 0; // 版权状态 0:有版权 1:无版权，可推荐 2:无版权，黑名单，不可推荐
    private long storeTime = -1L; // 入库时间
    private String md5 = ""; // 外网新闻url的md5值
    private String ooid = ""; // 外网新闻源oid值
    private String oldDs = ""; // 原始url
    private Map<String, Double> tag = null;
    private String extra = ""; // 额外信息(json)

    private String publicationName = ""; // 刊物名称
    private String typeArea = ""; // 域类型
    private String channel = ""; // 频道
    private String sub_channel = ""; // 子频道
    private String extra_type = ""; // 额外类型
    private String crawl_type = "";
    private String stock_code = "";//新闻所属的股票编码
    private String co_operation = "0";//是否为合作媒体
    private String pub_type = "";
    
    private String isHasVideo = "0"; // has video
    private String mediaType = ""; // Media type A B C
    private String tags = ""; // tag list
    private String  themeImgUrl = ""; // 图片url,lxj,2015-04-27
    private String source_from = ""; // news data source
 
    private String permit_export = "0"; //1是本地，默认0,不入库
    
    private String  source_mark ="1";//0：编辑人工 1：推荐抓取 2：mp同步
    
    //新加字段，入库，存newstemp表，news表
    private String  have_ImgUrl ="0";//0：没有头图：1 有头图；
    private String  image_judge ="0";//0：需要cms判断：1 不需要判断；
    
    private String  top_ImgUrl ="";//真实头图url
    private String  fromUrl ="";//种子来源url
    private double co_score = 0.0;//新闻分数
    //以下不入库
    private int QR_count = 0;//图片二维码个数
    
    private int normal_image_count = 0;//正常图片个数
    
    private int small_image_count = 0;//图片二维码个数
  //新加入库属性  
    private String is_topic_image = "0";//是否为topic补图
    private String judge_goods = "";
    private String stock_title = "";
    private int oldNewscore = 0;
    private int currentNewscore = 0;
    private String source = ""; // 新闻发布媒体，新闻最初来源
    
    
    private long changettl = 259200000l; // 新闻ttl保存时间
    
    private String geo_code_suspected = "-1"; //疑似地域新闻属性，默认-1 ，0代表是疑似地域新闻
    
    private String geolabel = "";//疑似地域权重
    
    private int type =0;
    private double confidence = 1;//type表示分类结果（1是标题党，0是非标题党），confidence代表可信度。
    
  //stock push sign
  	public static final int STOCKPUSH = 15;//stock push sign
  	
  	
  	public static final int GOODS = 16;
  	
  //push type
  	public static final int STOCKHQ = 5;
  	public static final int STOCKNOTICE = 6;
  	
  	//for video
  	private long video_id=0l;
  	private String comments = "";
  	private String count = "";
  	private String videoSize = "";
  	private long video_length = 0l;
  	private long upload_dt = 0l;
  	private String arg0 = "";
  	private int videoStatus = 0;
  	private String h_downloadurl="";
  	private String [] h_clipsDuration = new String[10];
	private String []  h_clipsBytes = new String[10];
	
	private String n_downloadurl = "";
	private String [] n_clipsDuration = new String[10];
	private String [] n_clipsBytes = new String[10];
	
	private String isvideo = "0";
	
	private String video_str_cate_code = "";
  	private String bigCover = "";
  	
 	
  	private String isIntelligentOffer = "0";
  	
  	private NewsPicResult[] group_pic = new NewsPicResult[0]; // 组图新闻图片
	//top_ImgUrl
	private NewsPicResult[] group_top_ImgUrl = new NewsPicResult[0]; //组图云存储地址

  	
  
    
    public NewsGroupResult2[] getG2() {
		return g2;
	}

	public void setG2(NewsGroupResult2[] g2) {
		this.g2 = g2;
	}
	
	public void setShouSouNewsOid(String oid, boolean store) {

		if (oid == null || oid.equals("")) {
			return;
		}
		try {
			//Integer.parseInt(oid);
		} catch (Throwable e) {
			return;
		}
		this.oid = oid;
	

	}
	public static class NewsGroupResult2 implements Serializable {

		private static final long serialVersionUID = -4160802850707355806L;

		private int gid = 0;
		private double w = 0;
		private String  name ="";

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public NewsGroupResult2() {
		}
		public NewsGroupResult2(int gid, double w) {
			this.gid = gid;
			this.w = w;
		}
		public NewsGroupResult2( double w,String name,int gid) {
			this.name = name;
			this.gid = gid;
			this.w = w;
		}

		public int getGid() {
			return gid;
		}

		public void setGid(int gid) {
			this.gid = gid;
		}

		public double getW() {
			return w;
		}

		public void setW(double w) {
			this.w = w;
		}

		@Override
		public String toString() {
			return "NewsGroupResult [name = "+name+",gid=" + gid + ", w=" + w + "]";
		}
	}
	
	public static class NewsGroupResult3 implements Serializable {



		/**
		 * 
		 */
		private static final long serialVersionUID = -5503963684833963359L;
		private long gid = 0;
		private double w = 0;
		private String  name ="";

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public NewsGroupResult3() {
		}
		public NewsGroupResult3(long gid, double w) {
			this.gid = gid;
			this.w = w;
		}
		public NewsGroupResult3( double w,String name,long gid) {
			this.name = name;
			this.gid = gid;
			this.w = w;
		}

		public long getGid() {
			return gid;
		}

		public void setGid(long gid) {
			this.gid = gid;
		}

		public double getW() {
			return w;
		}

		public void setW(double w) {
			this.w = w;
		}

		@Override
		public String toString() {
			return "NewsGroupResult [name = "+name+",gid=" + gid + ", w=" + w + "]";
		}
	}
    /**
     * @return the themeImgUrl
     */
    public String getThemeImgUrl() {
        return themeImgUrl;
    }

    /**
     * @param url
     */
    public void setThemeImgUrl(String url) {
        this.themeImgUrl = url;
    }
    
    /**
     * @return the publicationName
     */
    public String getPublicationName() {
        return publicationName;
    }

    /**
     * @param publicationName the publicationName to set
     */
    public void setPublicationName(String publicationName) {
        this.publicationName = publicationName;
    }

    /**
     * @return the typeArea
     */
    public String getTypeArea() {
        return typeArea;
    }

    /**
     * @param typeArea the typeArea to set
     */
    public void setTypeArea(String typeArea) {
        this.typeArea = typeArea;
    }

    /**
     * @return the channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * @return the sub_channel
     */
    public String getSub_channel() {
        return sub_channel;
    }

    /**
     * @param sub_channel the sub_channel to set
     */
    public void setSub_channel(String sub_channel) {
        this.sub_channel = sub_channel;
    }


    public long getStoreTime() {
        return storeTime;
	}

	public void setStoreTime(long storeTime) {
		this.storeTime = storeTime;
	}

	private String sharer = "";

	public String getSharer() {
		return sharer;
	}

	public void setSharer(String sharer) {
		this.sharer = sharer;
	}

	/**
	 * @return the site
	 */
	public String getSite() {
		return site;
	}

	/**
	 * @param site
	 *            the site to set
	 */
	public void setSite(String site) {
		this.site = site;
	}

	public MrdNewsObject_Kafka() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nid == null) ? 0 : nid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MrdNewsObject_Kafka other = (MrdNewsObject_Kafka) obj;
		if (nid == null) {
			if (other.nid != null)
				return false;
		} else if (!nid.equals(other.nid))
			return false;
		return true;
	}


	private boolean ShouStored = false;


	private boolean shousoustored = false;


	public String getNid() {
		return nid;
	}

	public void setNid(String nid, boolean store) {
		this.nid = nid;
	
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public void setOid(String oid, boolean store) {

		if (oid == null || oid.equals("")) {
			return;
		}
		try {
			// Integer.parseInt(oid);
		} catch (Throwable e) {
			return;
		}
		this.oid = oid;
	

	}

	public String getDs() {
		return ds;
	}

	public void setDs(String ds) {
		this.ds = ds;
	}

	public long getCt() {
		return ct;
	}

	public long getOCt() {
		return oct;
	}

	public void setCt(long ct) {
		this.ct = ct;
	}

	public void setOCt(long oct) {
		this.oct = oct;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getSu() {
		return su;
	}

	public void setSu(String su) {
		this.su = su;
	}

	public String getCo() {
		return co;
	}

	public void setCo(String co) {
		this.co = co;
	}

	public String getMe() {
		return me;
	}

	public void setMe(String me) {
		this.me = me;
	}

	public int[] getCh() {
		return ch;
	}

	public void setCh(int[] ch) {
		this.ch = ch;
	}

	public String getEs() {
		return es;
	}

	public void setEs(String es) {
		this.es = es;
	}

	public NewsKeyWordResult[] getKw() {
		return kw;
	}

	public void setKw(NewsKeyWordResult[] kw) {
		this.kw = kw;
	}

	public NewsGroupResult[] getG() {
		return g;
	}

	public void setG(NewsGroupResult[] g) {
		this.g = g;
	}

	public NewsPicResult[] getPic() {
		return pic;
	}

	public void setPic(NewsPicResult[] pic) {
		this.pic = pic;
	}
	
	public static class NewsKeyWordResult implements
			Comparable<NewsKeyWordResult>, Serializable {

		private static final long serialVersionUID = 2841196493400287273L;

		private String k;
		private double w;

		public NewsKeyWordResult() {
		}

		public NewsKeyWordResult(String k, double w) {
			this.k = k;
			this.w = w;
		}

		public String getK() {
			return k;
		}

		public void setK(String k) {
			this.k = k;
		}

		public double getW() {
			return w;
		}

		public void setW(double w) {
			this.w = w;
		}

		@Override
		public String toString() {
			return "NewsKeyWordResult [k=" + k + ", w=" + w + "]";
		}

		public int compareTo(NewsKeyWordResult o) {
			if (this.w > o.w) {
				return -1;
			} else if (this.w == o.w) {
				return 0;
			} else {
				return 1;
			}
		}
	}

	public static class NewsKeyWordResultOriginal implements
			Comparable<NewsKeyWordResultOriginal>, Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -7655362069931639652L;
		/**
				 * 
				 */
	
		private String k;
		private double w;
		private double ow;

		public NewsKeyWordResultOriginal() {
		}

		public NewsKeyWordResultOriginal(String k,double w ,double ow) {
			this.k = k;
			this.ow = ow;
			this.w = w;
		}

		public String getK() {
			return k;
		}

		public void setK(String k) {
			this.k = k;
		}

		public double getW() {
			return w;
		}

		public void setW(double w) {
			this.w = w;
		}

		public double getOw() {
			return ow;
		}

		public void setOw(double ow) {
			this.ow = ow;
		}

		@Override
		public String toString() {
			return "NewsKeyWordResultOriginal [k=" + k + ", w=" + w + ", ow="
					+ ow + "]";
		}

		public int compareTo(NewsKeyWordResultOriginal o) {
			// TODO Auto-generated method stub
			return 0;
		}


	}
	public static class NR_NewsKeyWordResult extends NewsKeyWordResult {

		private static final long serialVersionUID = 2841196493400287273L;

		private int id;
		private String k;
		private double w;

		public NR_NewsKeyWordResult() {
		}

		public NR_NewsKeyWordResult(int id, String k, double w) {
			this.id = id;
			this.k = k;
			this.w = w;
		}

		public int getId(){
			return id;
		}
		
		public void setId(int id){
			this.id = id;
		}
		
		public String getK() {
			return k;
		}

		public void setK(String k) {
			this.k = k;
		}

		public double getW() {
			return w;
		}

		public void setW(double w) {
			this.w = w;
		}

		@Override
		public String toString() {
			return "NewsKeyWordResult [id=" + id + " k=" + k + ", w=" + w + "]";
		}

		@Override
		public int compareTo(NewsKeyWordResult o) {
			if (this.w > o.w) {
				return -1;
			} else if (this.w == o.w) {
				return 0;
			} else {
				return 1;
			}
		}
	}

	public static class NewsGroupResult implements Serializable {

		private static final long serialVersionUID = -4160802850707355805L;

		private int gid;
		private double w;

		public NewsGroupResult() {
		}

		public NewsGroupResult(int gid, double w) {
			this.gid = gid;
			this.w = w;
		}

		public int getGid() {
			return gid;
		}

		public void setGid(int gid) {
			this.gid = gid;
		}

		public double getW() {
			return w;
		}

		public void setW(double w) {
			this.w = w;
		}

		@Override
		public String toString() {
			return "NewsGroupResult [gid=" + gid + ", w=" + w + "]";
		}
	}

	public static class NewsVideoResult implements Serializable {

		private static final long serialVersionUID = 3133197627980362082L;

		private String a = "";
		private String d = "";
		private String u = "";

		public NewsVideoResult() {
		}

		public NewsVideoResult(String a, String d, String u) {
			super();
			this.a = a;
			this.d = d;
			this.u = u;
		}

		public String getA() {
			return a;
		}

		public void setA(String a) {
			this.a = a;
		}

		public String getD() {
			return d;
		}

		public void setD(String d) {
			this.d = d;
		}

		public String getU() {
			return u;
		}

		public void setU(String u) {
			this.u = u;
		}

		@Override
		public String toString() {
			return "NewsVideoResult [a=" + a + ", d=" + d + ", u=" + u + "]";
		}

	}

	public static class NewsPicResult implements Serializable {

		private static final long serialVersionUID = -2325181690025015762L;

		private String a = ""; // 标题
		private String d = ""; // 描述
		private String u = ""; // url
		private int l; // 长度
		private int w; // 宽度

		public NewsPicResult() {
		}

		public NewsPicResult(String a, String d, String u, int l, int w) {
			this.a = a;
			this.d = d;
			this.u = u;
			this.l = l;
			this.w = w;
		}

		public String getA() {
			return a;
		}

		public void setA(String a) {
			this.a = a;
		}

		public String getD() {
			return d;
		}

		public void setD(String d) {
			this.d = d;
		}

		public String getU() {
			return u;
		}

		public void setU(String u) {
			this.u = u;
		}

		public int getL() {
			return l;
		}

		public void setL(int l) {
			this.l = l;
		}

		public int getW() {
			return w;
		}

		public void setW(int w) {
			this.w = w;
		}

		@Override
		public String toString() {
			return "NewsPicResult [a=" + a + ", d=" + d + ", u=" + u + ", l="
					+ l + ", w=" + w + "]";
		}

	}

//	@Override
//	public String toString() {
//		return "MrdNewsObject [nid=" + nid + ", oid=" + oid + ", ds=" + ds
//				+ ", ct=" + ct + ", t=" + t + ", su=" + su + ", co=" + co
//				+ ", me=" + me + ", ch=" + Arrays.toString(ch) + ", kw="
//				+ Arrays.toString(kw) + ", g=" + Arrays.toString(g) + ", pic="
//				+ Arrays.toString(pic) + ", cl=" + cl + ", lz=" + lz
//				+ ", rank=" + rank + ", es=" + es + ", chan=" + chan
//				+ ", from=" + newsfrom + ", bread=" + bread + ", pageType="
//				+ pageType + ", newsType=" + newsType + ", siteRank="
//				+ siteRank + ", st=" + st + ", createime=" + createTime
//				+ ", remark=" + remark + ", subName=" + subName + ", subType="
//				+ ", subType=" + subType + ", site=" + site + "]";
//	}

	public String getCl() {
		return cl;
	}

	public void setCl(String cl) {
		this.cl = cl;
	}

	public String getLz() {
		return lz;
	}

	public void setLz(String lz) {
		this.lz = lz;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getChan() {
		return chan;
	}

	public void setChan(String chan) {
		this.chan = chan;
	}

	public String getFrom() {
		return newsfrom;
	}

	public void setFrom(String from) {
		this.newsfrom = from;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBread() {
		return bread;
	}

	public void setBread(String bread) {
		this.bread = bread;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getPageRank() {
		return pageRank;
	}

	public void setPageRank(String pageRank) {
		this.pageRank = pageRank;
	}

	public NewsVideoResult[] getVideo() {
		return video;
	}

	public void setVideo(NewsVideoResult[] video) {
		this.video = video;
	}

	public String[] getRadio() {
		return radio;
	}

	public void setRadio(String[] radio) {
		this.radio = radio;
	}

	public int getSt() {
		return st;
	}

	public void setSt(int st) {
		this.st = st;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSiteRank() {
		return siteRank;
	}

	public void setSiteRank(String siteRank) {
		this.siteRank = siteRank;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String toSimpleJSON() {
		JSONObject json = new JSONObject();
		json.put("nid", nid);
		json.put("oid", oid);
		json.put("ds", ds);
		json.put("ct", ct);
		json.put("t", t);
		json.put("me", me);
		json.put("ch", JSONArray.fromObject(ch));
		json.put("pic", JSONArray.fromObject(pic));
		json.put("kw", JSONArray.fromObject(kw));
		json.put("cl", cl);
		json.put("sub_cl", sub_cl);
		json.put("lz", lz);
		json.put("loc_score", loc_score);
		json.put("rank", rank);
		json.put("from", newsfrom);
		json.put("siteRank", siteRank);
		json.put("it", idtype);
		json.put("st", st);
		json.put("createTime", createTime);
		json.put("remark", remark);
		json.put("subName", subName);
		json.put("subType", subType);
		json.put("site", site);
		json.put("g", JSONArray.fromObject(g));
		json.put("mr", mediarank);
		json.put("status", status);
		json.put("reason", reason);
		json.put("copyright", copyright);
		json.put("oldDs", oldDs);
		json.put("isHasVideo", isHasVideo);
		json.put("stock_code", stock_code);
		json.put("co_operation", co_operation);
		json.put("g2", JSONArray.fromObject(g2));

		return json.toString();
	}

	public static MrdNewsObject_Kafka fromSimpleJSON(String json_str) {
		JSONObject json = JSONObject.fromObject(json_str);
		MrdNewsObject_Kafka news_simple = new MrdNewsObject_Kafka();
		news_simple.setNid(json.getString("nid"), false);
		news_simple.setOid(json.getString("oid"), false);
		news_simple.setDs(json.getString("ds"));
		news_simple.setCt(json.getLong("ct"));
		news_simple.setT(json.getString("t"));
		news_simple.setMe(json.getString("me"));
		news_simple.setCl(json.getString("cl"));
		news_simple.setSub_cl(json.getString("sub_cl"));
		news_simple.setLz(json.getString("lz"));
		news_simple.setLoc_score(json.getDouble("loc_score"));
		news_simple.setRank(json.getInt("rank"));
		news_simple.setFrom(json.getString("from"));
		news_simple.setSiteRank(json.getString("siteRank"));
		news_simple.setSt(json.getInt("st"));
		news_simple.setCreateTime(json.getLong("createTime"));
		news_simple.setRemark(json.getString("remark"));
		news_simple.setSubName(json.getString("subName"));
		news_simple.setSubType(json.getString("subType"));
		news_simple.setSite(json.getString("site"));
		news_simple.setMediarank(json.getDouble("mr"));
		news_simple.setIdtype(json.getInt("it"));
		news_simple.setStatus(json.getInt("status"));
		news_simple.setReason(json.getInt("reason"));
		news_simple.setCopyright(json.getInt("copyright"));
		news_simple.setStock_code(json.getString("stock_code"));
		news_simple.setCo_operation(json.getString("co_operation"));

		return news_simple;
	}

	/**
	 * @return the copyright
	 */
	public int getCopyright() {
		return copyright;
	}

	/**
	 * @param copyright
	 *            the copyright to set
	 */
	public void setCopyright(int copyright) {
		this.copyright = copyright;
	}

	/**
	 * @return the mediarank
	 */
	public double getMediarank() {
		return mediarank;
	}

	/**
	 * @param mediarank
	 *            the mediarank to set
	 */
	public void setMediarank(double mediarank) {
		this.mediarank = mediarank;
	}

	/**
	 * @return the idtype
	 */
	public int getIdtype() {
		return idtype;
	}

	/**
	 * @param idtype
	 *            the idtype to set
	 */
	public void setIdtype(int idtype) {
		this.idtype = idtype;
	}

	public static void main(String[] args) {
		MrdNewsObject_Kafka m = new MrdNewsObject_Kafka();
		NewsGroupResult[] a = new NewsGroupResult[2];
		int[] cc = new int[5];
		cc[0] = 1;
		cc[1] = 2;
		m.setCh(cc);
		m.setG(a);
		a[0] = new NewsGroupResult();
		a[1] = new NewsGroupResult();
		a[0].setGid(111);
		a[0].setW(0.2123);
		a[1].setGid(555);
		a[1].setW(0.2123);
		System.out.println(m.toSimpleJSON());

	}

	/**
	 * @return the loc_score
	 */
	public double getLoc_score() {
		return loc_score;
	}

	/**
	 * @param loc_score
	 *            the loc_score to set
	 */
	public void setLoc_score(double loc_score) {
		this.loc_score = loc_score;
	}

	/**
	 * @return the md5
	 */
	public String getMd5() {
		return md5;
	}

	/**
	 * @param md5
	 *            the md5 to set
	 */
	public void setMd5(String md5) {
		this.md5 = md5;
	}

	/**
	 * @return the ooid
	 */
	public String getOoid() {
		return ooid;
	}

	/**
	 * @param ooid
	 *            the ooid to set
	 */
	public void setOoid(String ooid) {
		this.ooid = ooid;
	}

	/**
	 * @return the oldDs
	 */
	public String getOldDs() {
		return oldDs;
	}

	/**
	 * @param oldDs
	 *            the oldDs to set
	 */
	public void setOldDs(String oldDs) {
		this.oldDs = oldDs;
	}

	/**
	 * @return the tag
	 */
	public Map<String, Double> getTag() {
		return tag;
	}

	/**
	 * @param tag
	 *            the tag to set
	 */
	public void setTag(Map<String, Double> tag) {
		this.tag = tag;
	}

	/**
	 * @return the extra
	 */
	public String getExtra() {
		return extra;
	}

	/**
	 * @param extra
	 *            the extra to set
	 */
	public void setExtra(String extra) {
		this.extra = extra;
	}

	/**
	 * @return the sub_cl
	 */
	public String getSub_cl() {
		return sub_cl;
	}

	/**
	 * @param sub_cl
	 *            the sub_cl to set
	 */
	public void setSub_cl(String sub_cl) {
		this.sub_cl = sub_cl;
	}

	public String toHiveFormat(boolean show_body) {
		return String
				.format("%s\t%s\t%s\t%d\t%d\t%s\t%s\t%s\t%s\t%s\t%s\t%d\t%s\t%s\t%d\t%s\t%s\t%d\t%d\t%s\t%s\t%s\t%d\t%d\t%s\t%s\t%d\t%s\t%s\t%d\t%s\t%s\t%s\t%s\t%s\t%s",
						nid, oid, ds, ct, oct, t.replaceAll("\t|\n", ""),
						su.replaceAll("\t|\n", ""), me.replaceAll("\t|\n", ""),
						cl, sub_cl, lz, rank, es, newsfrom, status, "",
						siteRank, st, createTime, subName.replaceAll("\t|\n", ""), subType, site,
						copyright, storeTime, "", "", reason, co.replaceAll("\t|\n", ""), source_from,normal_image_count,
						JSONArray.fromObject(g2).toString().replaceAll("\t|\n", ""),source_mark,mediaType,pageType,
						JSONArray.fromObject(kw).toString().replaceAll("\t|\n", ""),stock_code);
	}
	public String alltoHiveFormat(boolean show_body) {
		return String
				.format("%s\t%s\t%s\t%d\t%d\t%s\t%s\t%s\t%s\t%s\t%s\t%d\t%s\t%s\t%d\t%s\t%s\t%d\t%d\t%s\t%s\t%s\t%d\t%d\t%s\t%s\t%d\t%s\t%s\t%d\t%s\t%s\t%s\t%s\t%s\t%s",
						nid, oid, ds, ct, oct, t.replaceAll("\t|\n", ""),
						su.replaceAll("\t|\n", ""), me.replaceAll("\t|\n", ""),
						cl, sub_cl, lz, rank, es, newsfrom, status, "",
						siteRank, st, createTime, subName.replaceAll("\t|\n", ""), subType, site,
						copyright, storeTime, "", "", reason, co.replaceAll("\t|\n", ""), source_from,normal_image_count,JSONArray.fromObject(g2).toString().replaceAll("\t|\n", ""),source_mark,mediaType,pageType,JSONArray.fromObject(kw).toString().replaceAll("\t|\n", ""),stock_code);
	}
	/**
	 * @return the reason
	 */
	public int getReason() {
		return reason;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(int reason) {
		this.reason = reason;
	}

    /**
     * @return the extra_type
     */
    public String getExtra_type() {
        return extra_type;
    }

    /**
     * @param extra_type the extra_type to set
     */
    public void setExtra_type(String extra_type) {
        this.extra_type = extra_type;
    }

    /**
     * @return the isHasVideo
     */
    public String getIsHasVideo() {
        return isHasVideo;
    }

    /**
     * @param isHasVideo the isHasVideo to set
     */
    public void setIsHasVideo(String isHasVideo) {
        this.isHasVideo = isHasVideo;
    }

    /**
     * @return the pub_type
     */
    public String getPub_type() {
        return pub_type;
    }

    /**
     * @param pub_type the pub_type to set
     */
    public void setPub_type(String pub_type) {
        this.pub_type = pub_type;
    }

    /**
     * @return the crawl_type
     */
    public String getCrawl_type() {
        return crawl_type;
    }

    /**
     * @param crawl_type the crawl_type to set
     */
    public void setCrawl_type(String crawl_type) {
        this.crawl_type = crawl_type;
    }
    
    /**
     * @return the co_operation
     */
    public String getCo_operation() {
        return co_operation;
    }

    /**
     * @param stock_code the stock_code to set
     */
    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }
    
    /**
     * @return the stock_code
     */
    public String getStock_code() {
        return stock_code;
    }

    /**
     * @param co_operation the co_operation to set
     */
    public void setCo_operation(String co_operation) {
        this.co_operation = co_operation;
    }

    /**
     * @return the mediaType
     */
    public String getMediaType() {
        return mediaType;
    }

    /**
     * @param mediaType the mediaType to set
     */
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    /**
     * @return the tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * @return the source_from
     */
    public String getSource_from() {
        return source_from;
    }

    /**
     * @param source_from the source_from to set
     */
    public void setSource_from(String source_from) {
        this.source_from = source_from;
    }

	public String getPermit_export() {
		return permit_export;
	}

	public void setPermit_export(String permit_export) {
		this.permit_export = permit_export;
	}

	@Override
	public String toString() {
		return "MrdNewsObject_Kafka [nid=" + nid + ", oid=" + oid
				+ ", shousoid=" + shousoid + ", ds=" + ds + ", ct=" + ct
				+ ", oct=" + oct + ", t=" + t + ", su=" + su + ", co=" + co
				+ ", me=" + me + ", ch=" + Arrays.toString(ch) + ", kw="
				+ Arrays.toString(kw) + ", kwold=" + Arrays.toString(kwold)
				+ ", kworiginal=" + Arrays.toString(kworiginal) + ", g="
				+ Arrays.toString(g) + ", g2=" + Arrays.toString(g2) + ", g3="
				+ Arrays.toString(g3) + ", pic=" + Arrays.toString(pic)
				+ ", video=" + Arrays.toString(video) + ", radio="
				+ Arrays.toString(radio) + ", cl=" + cl + ", sub_cl=" + sub_cl
				+ ", lz=" + lz + ", loc_score=" + loc_score + ", rank=" + rank
				+ ", es=" + es + ", chan=" + chan + ", newsfrom=" + newsfrom
				+ ", status=" + status + ", reason=" + reason + ", bread="
				+ bread + ", pageType=" + pageType + ", pageRank=" + pageRank
				+ ", newsType=" + newsType + ", siteRank=" + siteRank
				+ ", mediarank=" + mediarank + ", idtype=" + idtype + ", st="
				+ st + ", createTime=" + createTime + ", remark=" + remark
				+ ", subName=" + subName + ", subType=" + subType + ", site="
				+ site + ", copyright=" + copyright + ", storeTime="
				+ storeTime + ", md5=" + md5 + ", ooid=" + ooid + ", oldDs="
				+ oldDs + ", tag=" + tag + ", extra=" + extra
				+ ", publicationName=" + publicationName + ", typeArea="
				+ typeArea + ", channel=" + channel + ", sub_channel="
				+ sub_channel + ", extra_type=" + extra_type + ", crawl_type="
				+ crawl_type + ", stock_code=" + stock_code + ", co_operation="
				+ co_operation + ", pub_type=" + pub_type + ", isHasVideo="
				+ isHasVideo + ", mediaType=" + mediaType + ", tags=" + tags
				+ ", themeImgUrl=" + themeImgUrl + ", source_from="
				+ source_from + ", permit_export=" + permit_export
				+ ", source_mark=" + source_mark + ", have_ImgUrl="
				+ have_ImgUrl + ", image_judge=" + image_judge
				+ ", top_ImgUrl=" + top_ImgUrl + ", fromUrl=" + fromUrl
				+ ", co_score=" + co_score + ", QR_count=" + QR_count
				+ ", normal_image_count=" + normal_image_count
				+ ", small_image_count=" + small_image_count
				+ ", is_topic_image=" + is_topic_image + ", judge_goods="
				+ judge_goods + ", stock_title=" + stock_title
				+ ", oldNewscore=" + oldNewscore + ", currentNewscore="
				+ currentNewscore + ", source=" + source + ", changettl="
				+ changettl + ", geo_code_suspected=" + geo_code_suspected
				+ ", geolabel=" + geolabel + ", type=" + type + ", confidence="
				+ confidence + ", video_id=" + video_id + ", comments="
				+ comments + ", count=" + count + ", videoSize=" + videoSize
				+ ", video_length=" + video_length + ", upload_dt=" + upload_dt
				+ ", arg0=" + arg0 + ", videoStatus=" + videoStatus
				+ ", h_downloadurl=" + h_downloadurl + ", h_clipsDuration="
				+ Arrays.toString(h_clipsDuration) + ", h_clipsBytes="
				+ Arrays.toString(h_clipsBytes) + ", n_downloadurl="
				+ n_downloadurl + ", n_clipsDuration="
				+ Arrays.toString(n_clipsDuration) + ", n_clipsBytes="
				+ Arrays.toString(n_clipsBytes) + ", isvideo=" + isvideo
				+ ", video_str_cate_code=" + video_str_cate_code
				+ ", bigCover=" + bigCover + ", isIntelligentOffer="
				+ isIntelligentOffer + ", group_pic="
				+ Arrays.toString(group_pic) + ", group_top_ImgUrl="
				+ Arrays.toString(group_top_ImgUrl) + ", sharer=" + sharer
				+ ", ShouStored=" + ShouStored + ", shousoustored="
				+ shousoustored + "]";
	}

	public String getSource_mark() {
		return source_mark;
	}

	public void setSource_mark(String source_mark) {
		this.source_mark = source_mark;
	}

	public String getHave_ImgUrl() {
		return have_ImgUrl;
	}

	public void setHave_ImgUrl(String have_ImgUrl) {
		this.have_ImgUrl = have_ImgUrl;
	}

	public String getImage_judge() {
		return image_judge;
	}

	public void setImage_judge(String image_judge) {
		this.image_judge = image_judge;
	}

	public String getTop_ImgUrl() {
		return top_ImgUrl;
	}

	public void setTop_ImgUrl(String top_ImgUrl) {
		this.top_ImgUrl = top_ImgUrl;
	}

	public String getFromUrl() {
		return fromUrl;
	}

	public void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}

	public double getCo_score() {
		return co_score;
	}

	public void setCo_score(double co_score) {
		this.co_score = co_score;
	}

	public int getQR_count() {
		return QR_count;
	}

	public void setQR_count(int qR_count) {
		QR_count = qR_count;
	}

	public int getNormal_image_count() {
		return normal_image_count;
	}

	public void setNormal_image_count(int normal_image_count) {
		this.normal_image_count = normal_image_count;
	}

	public int getSmall_image_count() {
		return small_image_count;
	}

	public void setSmall_image_count(int small_image_count) {
		this.small_image_count = small_image_count;
	}

	public String getIs_topic_image() {
		return is_topic_image;
	}

	public void setIs_topic_image(String is_topic_image) {
		this.is_topic_image = is_topic_image;
	}

	public String getJudge_goods() {
		return judge_goods;
	}

	public void setJudge_goods(String judge_goods) {
		this.judge_goods = judge_goods;
	}

	public String getShousoid() {
		return shousoid;
	}

	public void setShousoid(String shousoid) {
		this.shousoid = shousoid;
	}
	public void setShousoid(String shousoid, boolean store) {

		if (shousoid == null || shousoid.equals("")) {
			return;
		}
		try {
			// Integer.parseInt(oid);
		} catch (Throwable e) {
			return;
		}
		this.shousoid = shousoid;
	

	}


	public String getStock_title() {
		return stock_title;
	}

	public void setStock_title(String stock_title) {
		this.stock_title = stock_title;
	}

	public NewsKeyWordResult[] getKwold() {
		return kwold;
	}

	public void setKwold(NewsKeyWordResult[] kwold) {
		this.kwold =kwold;
	}

	public int getOldNewscore() {
		return oldNewscore;
	}

	public void setOldNewscore(int oldNewscore) {
		this.oldNewscore = oldNewscore;
	}

	public int getCurrentNewscore() {
		return currentNewscore;
	}

	public void setCurrentNewscore(int currentNewscore) {
		this.currentNewscore = currentNewscore;
	}

	public NewsKeyWordResultOriginal[] getKworiginal() {
		return kworiginal;
	}

	public void setKworiginal(NewsKeyWordResultOriginal[] kworiginal) {
		this.kworiginal = kworiginal;
	}

	public long getChangettl() {
		return changettl;
	}

	public void setChangettl(long changettl) {
		this.changettl = changettl;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public NewsGroupResult3[] getG3() {
		return g3;
	}

	public void setG3(NewsGroupResult3[] g3) {
		this.g3 = g3;
	}

	public String getGeo_code_suspected() {
		return geo_code_suspected;
	}

	public void setGeo_code_suspected(String geo_code_suspected) {
		this.geo_code_suspected = geo_code_suspected;
	}

	public String getGeolabel() {
		return geolabel;
	}

	public void setGeolabel(String geolabel) {
		this.geolabel = geolabel;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getConfidence() {
		return confidence;
	}

	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

	public long getVideo_id() {
		return video_id;
	}

	public void setVideo_id(long video_id) {
		this.video_id = video_id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getVideoSize() {
		return videoSize;
	}

	public void setVideoSize(String videoSize) {
		this.videoSize = videoSize;
	}

	public long getVideo_length() {
		return video_length;
	}

	public void setVideo_length(long video_length) {
		this.video_length = video_length;
	}

	public long getUpload_dt() {
		return upload_dt;
	}

	public void setUpload_dt(long upload_dt) {
		this.upload_dt = upload_dt;
	}

	public String getArg0() {
		return arg0;
	}

	public void setArg0(String arg0) {
		this.arg0 = arg0;
	}

	public int getVideoStatus() {
		return videoStatus;
	}

	public void setVideoStatus(int videoStatus) {
		this.videoStatus = videoStatus;
	}

	public String getH_downloadurl() {
		return h_downloadurl;
	}

	public void setH_downloadurl(String h_downloadurl) {
		this.h_downloadurl = h_downloadurl;
	}

	public String[] getH_clipsDuration() {
		return h_clipsDuration;
	}

	public void setH_clipsDuration(String[] h_clipsDuration) {
		this.h_clipsDuration = h_clipsDuration;
	}

	public String[] getH_clipsBytes() {
		return h_clipsBytes;
	}

	public void setH_clipsBytes(String[] h_clipsBytes) {
		this.h_clipsBytes = h_clipsBytes;
	}

	public String getN_downloadurl() {
		return n_downloadurl;
	}

	public void setN_downloadurl(String n_downloadurl) {
		this.n_downloadurl = n_downloadurl;
	}

	public String[] getN_clipsDuration() {
		return n_clipsDuration;
	}

	public void setN_clipsDuration(String[] n_clipsDuration) {
		this.n_clipsDuration = n_clipsDuration;
	}

	public String[] getN_clipsBytes() {
		return n_clipsBytes;
	}

	public void setN_clipsBytes(String[] n_clipsBytes) {
		this.n_clipsBytes = n_clipsBytes;
	}

	public String getIsvideo() {
		return isvideo;
	}

	public void setIsvideo(String isvideo) {
		this.isvideo = isvideo;
	}

	public String getVideo_str_cate_code() {
		return video_str_cate_code;
	}

	public void setVideo_str_cate_code(String video_str_cate_code) {
		this.video_str_cate_code = video_str_cate_code;
	}

	public String getBigCover() {
		return bigCover;
	}

	public void setBigCover(String bigCover) {
		this.bigCover = bigCover;
	}

	public String getIsIntelligentOffer() {
		return isIntelligentOffer;
	}

	public void setIsIntelligentOffer(String isIntelligentOffer) {
		this.isIntelligentOffer = isIntelligentOffer;
	}

	public NewsPicResult[] getGroup_pic() {
		return group_pic;
	}

	public void setGroup_pic(NewsPicResult[] group_pic) {
		this.group_pic = group_pic;
	}

	public NewsPicResult[] getGroup_top_ImgUrl() {
		return group_top_ImgUrl;
	}

	public void setGroup_top_ImgUrl(NewsPicResult[] group_top_ImgUrl) {
		this.group_top_ImgUrl = group_top_ImgUrl;
	}




	



	
}
