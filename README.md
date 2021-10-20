
## 프로젝트 
문서 전자 결재 시스템 API 서버

## 기능
1. USER(유저) - 로그인
2. 문서 - 결재 문서 등록
3. 문서 - OUTBOX 결재 문서 조회 (* OUTBOX : 내가 생성한 문서 중 결재 진행 중인 문서)
4. 문서 - INBOX 결재 문서 조회 (* INBOX : 내가 결재를 해야 할 문서)
5. 문서 - ARCHIVE 결재 문서 조회 (* ARCHIVE : 내가 관여한 문서 중 결재가 완료(승인 또는 거절)된 문서)
6. 문서 - 결재 문서 내용 조회
7. 결재 - 결재자 등록
8. 결재 - 결재 문서 처리

## 실행방법
ApprovalApplication.java main 실행

## API Document
(1). USER(유저) - 로그인
- URL <br>
    /api/userLogin
- Method <br>
    POST
- URL Params <br>
    userId=[String] : 유저ID<br>
    password=[String] : 비밀번호
- Success Response
    ```
    {
    "success": true,
    "response":{
        "userId": "No5",
        "password": "1234"
        },
    "error": null
    }
    
    ```
- Error Response
    ```
    {
    "success": false,
    "response": null,
    "error":{
    "message": "존재하지 않는 ID이거나 잘못된 비밀번호입니다.",
        "status": -1001
        }
    }
    
    ```
- Sample Request
    ```
    curl 'http://localhost:8000/api/createDocument' -i -X POST \
        -H 'Content-Type: application/json;charset=UTF-8' \
        -d '{"title":"document title", "type":"doc_type", "contents":"document contents", "writerId":"No3"}'
    ```
    
(2). 문서 - 결재 문서 등록
- URL <br>
    /api/createDocument
- Method <br>
    POST
- URL Params <br>
    writerId=[String] : 유저ID <br>
    title=[String] : 문서 제목 <br>
    type=[String] : 문서 유형 <br>
    contents=[String] : 문서 내용 <br>
- Success Response
    ```
    {
    "success": true,
    "response":{
        "docId": 3,
        "title": "과제 전형 결재 문서",
        "type": "긴급",
        "contents": "과제입니다",
        "writerId": "No1",
        "docStatus": "READY",
        "createdAt": "2021-10-11T21:22:05.287",
        "updatedAt": "2021-10-11T21:22:05.287"
        },
    "error": null
    }
    
    ```
- Error Response
    ```
    {
    "success": false,
    "response": null,
        "error":{
        "message": "해당 유저 ID가 존재하지 않습니다.",
        "status": -1003
        }
    }
    
    ```
- Sample Request
    ```
    curl 'http://localhost:8000/api/createDocument' -i -X POST \
            -H 'Content-Type: application/json;charset=UTF-8' \
            -d '{"title":"document title", "type":"doc_type", "contents":"document contents", "writerId":"No3"}'
    ```
(3). 문서 - OUTBOX 결재 문서 조회 (* OUTBOX : 내가 생성한 문서 중 결재 진행 중인 문서)
- URL <br>
    /api/getUserOutbox
- Method <br>
    POST
- URL Params <br>
    writerId=[String] : 유저ID 
- Success Response
    ```
    {
    "success": true,
    "response":[
            {
            "docId": 1,
            "title": "긴급 결재건",
            "type": "긴급",
            "contents": "결재 부탁드립니다",
            "writerId": "No5",
            "docStatus": "PROGRESS",
            "createdAt": "2021-10-09T21:00:19.368",
            "updatedAt": null
            }
        ],
    "error": null
    }
    
    ```
- Error Response
    ```
   {
   "success": false,
   "response": null,
   "error":{
       "message": "해당 유저 ID가 존재하지 않습니다.",
       "status": -1003
       }
   }
    
    ```
- Sample Request
    ```
    curl 'http://localhost:8000/api/getUserOutbox' -i -X POST \
                -H 'Content-Type: application/json;charset=UTF-8' \
                -d '{"writerId":"No2"}'
    ```

(4). 문서 - INBOX 결재 문서 조회 (* INBOX : 내가 결재를 해야 할 문서)
- URL <br>
    /api/getUserInbox
- Method <br>
    POST
- URL Params <br>
    userId=[String] : 유저ID 
- Success Response
    ```
    {
    "success": true,
    "response":[
        {
        "docId": 1,
        "title": "긴급 결재건",
        "type": "긴급",
        "contents": "결재 부탁드립니다",
        "writerId": "No5",
        "docStatus": "PROGRESS",
        "createdAt": "2021-10-09T21:00:19.368",
        "updatedAt": null
        }
    ],
    "error": null
    }
    
    ```
- Error Response
    ```
   {
   "success": false,
   "response": null,
   "error":{
       "message": "해당 유저 ID가 존재하지 않습니다.",
       "status": -1003
       }
   }
    
    ```
- Sample Request
    ```
    curl 'http://localhost:8000/api/getUserInbox' -i -X POST \
                -H 'Content-Type: application/json;charset=UTF-8' \
                -d '{"userId":"No3"}'
    ```

(5). 문서 - ARCHIVE 결재 문서 조회 (* ARCHIVE : 내가 관여한 문서 중 결재가 완료(승인 또는 거절)된 문서)
- URL <br>
    /api/getUserArchive
- Method <br>
    POST
- URL Params <br>
    userId=[String] : 유저ID 
- Success Response
    ```
    {
    "success": true,
    "response":[
        {
        "docId": 2,
        "title": "긴급 결재건2",
        "type": "긴급",
        "contents": "결재 부탁드립니다2222",
        "writerId": "No5",
        "docStatus": "REFUSE",
        "createdAt": "2021-10-10T21:00:19.377",
        "updatedAt": null
        }
    ],
    "error": null
    }
    
    ```
- Error Response
    ```
   {
   "success": false,
   "response": null,
   "error":{
       "message": "해당 유저 ID가 존재하지 않습니다.",
       "status": -1003
       }
   }
   ```
- Sample Request
    ```
    curl 'http://localhost:8000/api/getUserArchive' -i -X POST \
                -H 'Content-Type: application/json;charset=UTF-8' \
                -d '{"userId":"No3"}'
    ```

(6). 문서 - 결재 문서 내용 조회
- URL <br>
    /api/getDocument
- Method <br>
    POST
- URL Params <br>
    docId=[Integer] : 문서ID 
- Success Response
    ```
    {
    "success": true,
    "response":{
        "docId": 1,
        "title": "긴급 결재건",
        "type": "긴급",
        "contents": "결재 부탁드립니다",
        "writerId": "No5",
        "docStatus": "PROGRESS",
        "createdAt": "2021-10-09T21:00:19.368",
        "updatedAt": null
    },
    "error": null
    }
    
    ```
- Error Response
    ```
   {
   "success": false,
   "response": null,
   "error":{
       "message": "결재 문서 ID가 존재하지 않습니다.",
       "status": -1004
       }
   }
   ```
- Sample Request
    ```
    curl 'http://localhost:8000/api/getDocument' -i -X POST \
                -H 'Content-Type: application/json;charset=UTF-8' \
                -d '{"docId":3}'
    ```
   

(7). 결재 - 결재자 등록
- URL <br>
    /api/createSignUser
- Method <br>
    POST
- URL Params <br>
    docId=[Integer] : 문서ID <br>
    userId=[String] : 유저ID <br>
    signNo=[integer] : 결재 순번 
- Success Response
    ```
    {
    "success": true,
    "response":{
        "docId": 1,
        "userId": "No1",
        "signNo": 1,
        "signStatus": "PROGRESS",
        "opinion": null,
        "createdAt": "2021-10-11T21:38:50.559",
        "updatedAt": "2021-10-11T21:38:50.559"
    },
    "error": null
    }
    
    ```
- Error Response
    ```
   {
   "success": false,
   "response": null,
   "error":{
       "message": "해당 유저 ID가 존재하지 않습니다.",
       "status": -1003
       }
   }
   or
   {
   "success": false,
   "response": null,
   "error":{
       "message": "결재 문서 ID가 존재하지 않습니다.",
       "status": -1004
       }
   }
   or
   {
   "success": false,
   "response": null,
   "error":{
        message": "중복 된 결재 정보입니다.",
       "status": -1005
       }
   }
   or
   {
   "success": false,
   "response": null,
   "error":{
       "message": "이미 등록 된 결재자입니다.",
       "status": -1006
       }
   }
   or
   {
   "success": false,
   "response": null,
   "error":{
       "message": "이미 등록 된 결재순번입니다.",
       "status": -1007
       }
   }
   
   ```
- Sample Request
    ```
    curl 'http://localhost:8000/api/createSignUser' -i -X POST \
                -H 'Content-Type: application/json;charset=UTF-8' \
                -d '{"docId":3, "userId":"No3", "signNo": 1}'
    ```
(8). 결재 - 결재 문서 처리
- URL <br>
    /api/processSign
- Method <br>
    PATCH
- URL Params <br>
    docId=[Integer] : 문서ID <br>
    userId=[String] : 유저ID <br>
    signStatus=[String] : 문서 상태(승인-APPROVAL, 거절-REFUSE) <br>
    opinion=[String] : 의견 
- Success Response
    ```
    {
    "success": true,
    "response": true,
    "error": null
    }
    
    ```
- Error Response
    ```
    {
    "success": false,
    "response": null,
    "error":{
        "message": "해당 유저 ID가 존재하지 않습니다.",
        "status": -1003
        }
    }
    or
    {
    "success": false,
    "response": null,
    "error":{
        "message": "결재 문서 ID가 존재하지 않습니다.",
        "status": -1004
        }
    }
    or
   {
   "success": false,
   "response": null,
   "error":{
       "message": "결재 차례가 아닙니다.",
       "status": -1009
       }
   }
   or
   {
   "success": false,
   "response": null,
   "error":{
       "message": "결재문서 결재자가 아닙니다.",
       "status": -1011
       }
   }
   or 
   {
   "success": false,
   "response": null,
   "error":{
       "message": "결재정보가 없습니다.",
       "status": -1012
       }
   }
   or 
   {
   "success": false,
   "response": null,
   "error":{
       "message": "이미 만료된 결재문서입니다.",
       "status": -1008
       }
   }

   ```
- Sample Request
    ```
    curl 'http://localhost:8000/api/processSign' -i -X PATCH \
                -H 'Content-Type: application/json;charset=UTF-8' \
                -d '{"docId":3, "userId":"No3", "signStatus":"APPROVAL", "opinion":"approval"}'
    ```

## API 테스트 시나리오
ApprovalApplication.java 실행시 유저, 결재 문서, 결재 정보가 자동 등록 됩니다.<br>
등록 된 유저 : No1, No2, No3, No4, No5 <br>
등록 된 결재 문서 : doc_id 가 1,2 인 결재 문서가 등록 됨. <br>
결재 문서는 : READY(대기), PROGRESS(진행 중), APPROVAL(승인), REFUSE(거절) 4가지 상태 값이 있습니다. <br>
READY : 결재 문서만 등록하고 결재자는 등록하지 않은 상태. <br>
PROGRESS : 결재 문서 등록 후 결재자를 등록한 상태. <br>
APPROVAL : 결재 승인 상태 <br>
REFUSE : 결재 거절 상태  <br>

    - 결재 문서 생성
    1. 로그인
        userid : No2로 로그인합니다.
        API Document - (1) : /api/userLogin
        
        curl 'http://localhost:8000/api/userLogin' -i -X POST \
            -H 'Content-Type: application/json;charset=UTF-8' \
            -d '{
          "userId" : "No2",
          "password" : "1234"
        }'
        
        
    2. 문서 생성
        API Document - (2) : /api/createDocument
        
        curl 'http://localhost:8000/api/createDocument' -i -X POST \
            -H 'Content-Type: application/json;charset=UTF-8' \
            -d '{"title":"document title", "type":"doc_type", "contents":"document contents", "writerId":"No2"}'
        
        이때, 문서는 READY 상태
        
    3. 결재자 지정
        결재자는 No3으로 지정하며 결재 순번은 1로 설정합니다.
        API Document - (7) : /api/createSignUser
        
        curl 'http://localhost:8000/api/createSignUser' -i -X POST \
            -H 'Content-Type: application/json;charset=UTF-8' \
            -d '{"docId":3, "userId":"No3", "signNo": 1}'
        
        이때, 문서는 PROGRESS 상태
        
    4. OUTBOX 문서 호출
        API Document - (3) : /api/getUserOutbox
        
        curl 'http://localhost:8000/api/getUserOutbox' -i -X POST \
            -H 'Content-Type: application/json;charset=UTF-8' \
            -d '{"writerId":"No2"}'
    
    - 문서 결재
    1. 로그인
        userid : No3로 로그인합니다.
        API Document - (1) : /api/userLogin
        
        curl 'http://localhost:8000/api/createDocument' -i -X POST \
                -H 'Content-Type: application/json;charset=UTF-8' \
                -d '{"title":"document title", "type":"doc_type", "contents":"document contents", "writerId":"No3"}'
        
    2. 결재 해야할 문서(INBOX) 호출
        API Document - (4) : /api/getUserInbox
        
        curl 'http://localhost:8000/api/getUserInbox' -i -X POST \
            -H 'Content-Type: application/json;charset=UTF-8' \
            -d '{"userId":"No3"}'
            
    3. 문서 보기
        API Document - (6) : /api/getDocument
        
        curl 'http://localhost:8000/api/getDocument' -i -X POST \
            -H 'Content-Type: application/json;charset=UTF-8' \
            -d '{"docId":3}'
        
    4. 문서 승인/거절 하기
        API Document - (8) : /api/processSign
        
        curl 'http://localhost:8000/api/processSign' -i -X PATCH \
            -H 'Content-Type: application/json;charset=UTF-8' \
            -d '{"docId":3, "userId":"No3", "signStatus":"APPROVAL", "opinion":"approval"}'
        
        
    5. ARCHIVE 문서 호출
        API Document - (5) : /api/getUserArchive
        
        curl 'http://localhost:8000/api/getUserArchive' -i -X POST \
            -H 'Content-Type: application/json;charset=UTF-8' \
            -d '{"userId":"No3"}'