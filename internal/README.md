# MD 만들기


### 주별로 푼 문제들을 정리하고 확인하기 편리하게 하기 위함

```
.
├── README.md
├── archive
│   └── week1.md
├── internal
│   ├── README.md
│   ├── main.go
│   ├── md.go
│   └── problem.go
└── week1
    ├── BOJ11057
    │   ├── seho.java
    │   └── seorim.java
    └── README.md
    ...
```  
<br>

파일 구조를 읽고 week 디렉터리 안에 README.md 파일을 만들어 줍니다.  
양식은 아래와 같습니다

<br>

> # WEEK1
> 
> ### BOJ_11057: 오르막 수
> 
> |이름|풀이 링크|
> |--|--|
> |John| [link](BOJ_11057/john.java)
> |Jane| [link](BOJ_11057/jane.py)
> ---
  
    
<br>

문제 이름을 가져오기 위해 [solvedac 비공식 API](https://solvedac.github.io/unofficial-documentation/#/)를 사용하였습니다  

<br>




## 사용법   
####  루트 디렉터리에서    
#### `$ go run internal/main.go` 
