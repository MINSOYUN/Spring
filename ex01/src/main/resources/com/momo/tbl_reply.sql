---------- ���̺� ���� ----------
create table tbl_reply (
  rno number(10,0), 
  bno number(10,0) not null,
  reply varchar2(1000) not null,
  replyer varchar2(50) not null, 
  replyDate date default sysdate, 
  updateDate date default sysdate
);

---------- ������ ���� ------------
insert into tbl_reply(rno, bno, reply, replyer) values(seq_reply.nextval, 83, '���1234', '����ۼ���');

------- ������ ���� --------------
create sequence seq_reply;

--------- �⺻Ű ���� -----------
alter table tbl_reply add constraint pk_reply primary key (rno);

---------- �ܷ�Ű ���� --------
alter table tbl_reply  add constraint fk_reply_board  
foreign key (bno)  references  tbl_board (bno); 

insert into tbl_reply(rno, bno, reply, replyer, replydate) values(seq_reply.nextval, 85, '���', '�ۼ���', sysdate);
