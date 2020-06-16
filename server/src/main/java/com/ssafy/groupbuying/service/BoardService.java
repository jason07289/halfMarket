package com.ssafy.groupbuying.service;

import java.util.List;

import com.ssafy.groupbuying.vo.Board;
import com.ssafy.groupbuying.vo.Comment;
import com.ssafy.groupbuying.vo.Participants;
import com.ssafy.groupbuying.vo.Reputation;

public interface BoardService {
	// 게시판 CRUD
	public boolean insert(Board board);
	public boolean update(Board board);
	public boolean delete(long id);
	public Board getBoard(long id);
	public List<Board> getBoards();
	
	// 신청
	public int apply(long bid, long uid);
	// 취소
	public int cancel(long bid, long uid);
	
	// 댓글 CRUD
	public boolean insertComment(Comment com);
	public Comment deleteComment(long cid);
	public boolean updateComment(Comment com);
	public List<Comment> getComments(long bid);

	// 유저가 작성한 게시판
	public List<Board> search(long user_id);
	// 평가하기 ----- USER Controller로 이동해야함 
	public boolean rate(Reputation rep);
	
	//카테고리별 검색 (최대 3개까지 가능)
//	public List<Board> getCategoryBoard(String category);
//	public List<Board> getCategoryBoard(int type, String category);

	//키워드별 검색 (최대 3개까지 가능)
	public List<Board> getKeywordSearch(String keyword);
	
	
	// 관리자 페이지 - 년/월/주간 게시물 리스트
	public List<Board> searchByYear();
	public List<Board> searchByMonth();
	public List<Board> searchByWeek();
}
