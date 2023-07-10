---------- 테이블 생성 ----------
create table tbl_reply (
  rno number(10,0), 
  bno number(10,0) not null,
  reply varchar2(1000) not null,
  replyer varchar2(50) not null, 
  replyDate date default sysdate, 
  updateDate date default sysdate
);

---------- 데이터 삽입 ------------
insert into tbl_reply(rno, bno, reply, replyer) values(seq_reply.nextval, 83, '댓글1234', '댓글작성자');

------- 시퀀스 생성 --------------
create sequence seq_reply;

--------- 기본키 변경 -----------
alter table tbl_reply add constraint pk_reply primary key (rno);

---------- 외래키 변경 --------
alter table tbl_reply  add constraint fk_reply_board  
foreign key (bno)  references  tbl_board (bno); 

insert into tbl_reply(rno, bno, reply, replyer, replydate) values(seq_reply.nextval, 85, '댓글', '작성자', sysdate);
