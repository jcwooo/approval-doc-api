-- 유저 등록
insert into users (user_id, password) values ('No5', '1234');
insert into users (user_id, password) values ('No4', '1234');
insert into users (user_id, password) values ('No3', '1234');
insert into users (user_id, password) values ('No2', '1234');
insert into users (user_id, password) values ('No1', '1234');

-- 테스트용 결재 문서 등록
insert into document (title, type, contents, doc_status, user_id, created_at) values ('긴급 결재건', '긴급', '결재 부탁드립니다', 'PROGRESS', 'No5',now()-2);
insert into document (title, type, contents, doc_status, user_id, created_at) values ('긴급 결재건2', '긴급', '결재 부탁드립니다2222', 'REFUSE', 'No5',now()-1);

-- 테스트용 결재자 등록
insert into sign (doc_id, user_id, sign_no, sign_status, created_at) values (1,'No3',3,'PROGRESS',now());
insert into sign (doc_id, user_id, sign_no, sign_status, created_at) values (2,'No3',3,'REFUSE',now());
insert into sign (doc_id, user_id, sign_no, sign_status, created_at) values (1,'No4',4,'PROGRESS',now());
insert into sign (doc_id, user_id, sign_no, sign_status, created_at) values (1,'No5',5,'PROGRESS',now());
