package com.yc.biz.impl;

import redis.clients.jedis.Jedis;

import com.yc.bean.Reply;

public class ReplyRedisBizImpl {
	
	/**
	 * 点赞
	 */
	public Long glkReply(Reply reply ){
		Jedis jedis = new Jedis("localhost",6379) ;
		//判断jedis中是否有  1是否在键为topic中
		if(jedis.sismember("topic" +reply.getTopicid(), reply.getUid()  + "")   == false ){//判断是是否存在于键中
			jedis.sadd("topic" +reply.getTopicid(), reply.getUid()  + "");
			
		}else{
			jedis.smove("topic" +reply.getTopicid(), "trash" , reply.getUid()  + "");  //将值从键中取出，再存入另一个键中
		}
		
		Long glk = jedis.scard(     "topic" +reply.getTopicid()     )  ;
		return glk ;
	}
	
	/**
	 * 获取某天帖子的点赞数
	 * @param reply
	 * @return
	 */
	public Long  gettimes(Reply reply){
		
		Jedis jedis = new Jedis("localhost",6379) ;
		Long glk = jedis.scard(     "topic" +reply.getTopicid()     )  ;
		
		return glk ;
	}

}
