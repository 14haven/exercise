SELECT * FROM EMPLOYEE;

-- 1. 직원명과 주민번호를 조회함 단, 주민번호 9번째 자리부터 끝까지는 '*'문자로 채움
SELECT EMP_NAME, RPAD(SUBSTR(EMP_NO,1,8),14,'*')AS 주민번호 FROM EMPLOYEE;

-- 2. 직원명, 직급코드, 연봉(원) 조회 단, 연봉은 원 57,000,000으로 표시되게 함
-- 연봉은 보너스포인트가 적용된 1년치 급여임
SELECT EMP_NAME, JOB_CODE, TO_CHAR((SALARY*12)*NVL(1+BONUS,1),'L999,999,999') AS 연봉 FROM EMPLOYEE;

-- 3. 부서코드가 D5, D9인 직원들 중에서 2004년도에 입사한 직원의 수 조회함
SELECT * FROM EMPLOYEE;
DESC EMPLOYEE;

SELECT EMP_ID, EMP_NAME, DEPT_CODE, HIRE_DATE FROM EMPLOYEE 
    WHERE  DEPT_CODE IN('D5','D9') AND EXTRACT(YEAR FROM HIRE_DATE)=2004;

-- 4. 직원명, 입사일, 입사한 달의 근무일수 조회 단, 주말도 포함함
SELECT EMP_NAME, HIRE_DATE, LAST_DAY(HIRE_DATE)-HIRE_DATE 근무일수 FROM EMPLOYEE;

-- 5. 직원명, 부서코드, 생년월일, 나이(만) 조회
-- 단, 생년월일은 주민번호에서 추출해서, 00년, 00월, 00일로 출력되게 함
-- 나이는 주민번호에서 추출해서 날짜데이터로 변환한 다음, 계산함

SELECT EMP_NAME, DEPT_CODE, SUBSTR(EMP_NO,1,2) || '년' AS 생년 , SUBSTR(EMP_NO,3,2) || '월' AS 월 ,SUBSTR(EMP_NO,5,2) || '일' AS 일
    FROM EMPLOYEE;

SELECT * FROM EMPLOYEE;
DESC EMPLOYEE;

SELECT REPLACE(EMP_NO,SUBSTR(EMP_NO,1,2),1900+SUBSTR(EMP_NO,1,2)) FROM EMPLOYEE;
SELECT SUBSTR(REPLACE(EMP_NO,SUBSTR(EMP_NO,1,2),1900+SUBSTR(EMP_NO,1,2)),1,8) FROM EMPLOYEE;
SELECT TO_CHAR(TO_DATE('SUBSTR(EMP_NO,1,6)'),'YYYY-MM-DD') FROM EMPLOYEE;

SELECT TO_DATE('19941111') FROM DUAL;


-- 6. 직원들의 입사일로 부터 년도만 가지고, 각 년도별 입사인원수를 구하시오.
-- 아래의 년도에 입사한 인원수를 조회하시오.
-- -> to_char, decode, sum 사용

SELECT COUNT(*) 전체직원수 , SUM(DECODE(TO_CHAR(HIRE_DATE,'YYYY'), 2001, 1, 0)) AS "2001년", 
    SUM(DECODE(TO_CHAR(HIRE_DATE,'YYYY'), 2002, 1, 0)) AS "2002년",
    SUM(DECODE(TO_CHAR(HIRE_DATE,'YYYY'), 2003, 1, 0)) AS "2003년",
    SUM(DECODE(TO_CHAR(HIRE_DATE,'YYYY'), 2004, 1, 0)) AS "2004년"
    FROM EMPLOYEE;
    

-- 7. 부서코드가 D5이면 총무부, D6이면 기획부, D9이면 영업부로 처리하시오.
-- 단, 부서코드가 D5, D6, D9인 직원의 정보만 조회함
=> case 사용
-- 부서코드 기준 오름차순 정렬함


SELECT DEPT_CODE, CASE WHEN DEPT_CODE = 'D5' THEN '총무부'
    WHEN DEPT_CODE = 'D6' THEN '기획부'
    WHEN DEPT_CODE = 'D9' THEN '영업부'
    END AS 부서명
    FROM EMPLOYEE WHERE DEPT_CODE IN('D5','D6','D9') ORDER BY DEPT_CODE;
    DEPT_CODE = 'D5' OR DEPT_CODE ='D6' OR DEPT_CODE ='D9' ORDER BY DEPT_CODE;


