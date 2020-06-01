package com.ssafy.groupbuying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.groupbuying.repository.BoardRepository;
import com.ssafy.groupbuying.repository.ParticipantsRepository;
import com.ssafy.groupbuying.repository.ReputationRepository;
import com.ssafy.groupbuying.repository.UserRepository;
import com.ssafy.groupbuying.vo.Board;
import com.ssafy.groupbuying.vo.Comment;
import com.ssafy.groupbuying.vo.Participants;
import com.ssafy.groupbuying.vo.Reputation;
import com.ssafy.groupbuying.vo.User;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardRepository repo;
	
	@Autowired
	ParticipantsRepository prepo;

	@Autowired
	UserRepository urepo;
	
	@Autowired
	CommentRepository crepo;
	
	@Autowired
	ReputationRepository rrepo;
	
	@Override
	public boolean insert(Board board) {
		// 게시판을 작성하고
		repo.save(board);
		return true;
	}

	@Override
	public boolean update(Board board) {
		repo.save(board);
		return true;
	}

	@Override
	public boolean delete(long id) {
		// is_deleted 값에 true
		Board board = repo.findById(id);
		board.setDeleted(true);
		repo.save(board);
		return true;
	}

	@Override
	public Board getBoard(long id) {
		return repo.findById(id);
	}

	@Override
	public List<Board> getBoards() {
		return repo.findAll();
	}

	@Override
	public Participants apply(long bid, long uid) {
		// 기존에 있는 참가자수에 +1 후, UPDATE
		Board board = getBoard(bid);
		int attend = board.getParticipants();
		board.setParticipants(attend+1);
		update(board);
		
		// Participants 테이블에 저장
		User user = urepo.findById(uid);
		Participants newUser =new Participants();
		newUser.setUser(user);
		newUser.setBoard(board);
		
		prepo.save(newUser);
		
		return newUser;
	}

	@Override
	public Participants cancel(long bid, long uid) {
		// 기존에 있는 참가자수에 -1 후, UPDATE
		Board board = getBoard(bid);
		int attend = board.getParticipants();
		board.setParticipants(attend-1);
		update(board);
		
		Participants newUser = prepo.findByUserAndBoard(urepo.findById(uid), repo.findById(bid));
		System.out.println(newUser);
		prepo.delete(newUser);
		
		return newUser;
	}

	@Override
	public boolean insertComment(Comment com) {
		crepo.save(com);
		return true;
	}

	@Override
	public Comment deleteComment(long cid) {
		Comment com = crepo.findById(cid);
		crepo.delete(com);
		return com;
	}

	@Override
	public boolean updateComment(Comment com) {
		crepo.save(com);
		return true;
	}

	@Override
	public List<Comment> getComments(long bid) {
		// bid -> board -> CommentList
		Board board = repo.findById(bid);
		return crepo.findByBoard(board);
	}

	@Override
	public boolean rate(Reputation rep) {
		rrepo.save(rep);
		return true;
	}

	


}
