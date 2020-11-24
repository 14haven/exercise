-- [Additional SELECT - �Լ�]

-- 1. ������а�(�а��ڵ� 002) �л����� �й��� �̸�, ���� �⵵�� ���� �⵵�� ���� ������ ǥ���ϴ� SQL ������ �ۼ��Ͻÿ�.
-- (��, ����� "�й�", "�̸�", "���г⵵"�� ǥ�õǵ��� �Ѵ�.)
SELECT * FROM TB_STUDENT;
SELECT STUDENT_NO �й�, STUDENT_NAME �̸�, TO_CHAR(ENTRANCE_DATE, 'YYYY-MM-DD') ���г⵵ FROM TB_STUDENT
    WHERE DEPARTMENT_NO = '002' ORDER BY 3;
    
-- 2. �� ������б��� ���� �� �̸��� �� ���ڰ� �ƴ� ������ �� �� �ִٰ� �Ѵ�.
-- �� ������ �̸��� �ֹι�ȣ�� ȭ�鿡 ����ϴ� SQL ������ �ۼ��� ����. 
-- (* �̶� �ùٸ��� �ۼ��� SQL ������ ��� ���� ����� �ٸ��� ���� �� �ִ�. ������ �������� �����غ� ��)
SELECT * FROM TB_PROFESSOR;
SELECT PROFESSOR_NAME, PROFESSOR_SSN FROM TB_PROFESSOR
    WHERE PROFESSOR_NAME NOT LIKE '___';
    
-- 3. �� ������б��� ���� �������� �̸��� ���̸� ����ϴ� SQL ������ �ۼ��Ͻÿ�.
-- ��, �̶� ���̰� ���� �츶���� ���� ��� ������ ȭ�鿡 ��µǵ��� ����ÿ�.
-- (��, ���� �� 2000�� ���� ����ڴ� ������ ��� ����� "�����̸�", "����"�� �Ѵ�. ���̴� '��'���� ����Ѵ�.)

SELECT PROFESSOR_NAME �����̸�,
CASE WHEN TO_DATE(SUBSTR(PROFESSOR_SSN,1,6)) >= 2000/01/01 
    THEN FLOOR(MONTHS_BETWEEN(SYSDATE,TO_DATE(SUBSTR(PROFESSOR_SSN,1,6))-100)/12)
    WHEN TO_DATE(SUBSTR(PROFESSOR_SSN,1,6)) < 2000/01/01 
    THEN FLOOR(MONTHS_BETWEEN(SYSDATE,TO_DATE(SUBSTR(PROFESSOR_SSN,1,6))/12)
END AS ���� 
FROM TB_PROFESSOR
WHERE SUBSTR(PROFESSOR_SSN,8,1) = 1
ORDER BY 2;

-- 4. �������� �̸� �� ���� ������ �̸��� ����ϴ� SQL ������ �ۼ��Ͻÿ�.
-- ��� ����� "�̸�"�� �������� �Ѵ�. (���� 2���� ������ ���ٰ� �����Ͻÿ�)
SELECT * FROM TB_PROFESSOR;
SELECT SUBSTR(PROFESSOR_NAME,2) �̸� FROM TB_PROFESSOR; 

-- 5. �� ������б��� ����� �����ڸ� ���Ϸ��� �Ѵ�. ��� ã�Ƴ� ���ΰ�?
-- �̶�, 19�쿡 �����ϸ� ����� ���� ���� ������ �����Ѵ�.
SELECT * FROM TB_STUDENT;
SELECT STUDENT_NO, STUDENT_NAME FROM TB_STUDENT
WHERE FLOOR(MONTHS_BETWEEN(ENTRANCE_DATE,TO_DATE(SUBSTR(STUDENT_SSN,1,6)))/12)+1 = 20 ;

-- 6. 2020�� ũ���������� ���� �����ΰ�?
SELECT TO_CHAR(TO_DATE(20201225), 'day') FROM DUAL;

-- 7. TO_DATE('99/10/11', 'YY/MM/DD'), TO_DATE('49/10/11','YY/MM/DD') �� ���� �� �� �� �� �� ���� �ǹ��ұ�?
-- �� TO_DATE('99/10/11','RR/MM/DD'), TO_DATE('49/10/11','RR/MM/DD')�� ���� �� �� �� �� ������ �ǹ��ұ�?

SELECT TO_CHAR(TO_DATE('99/10/11'), 'YYYY/MM/DD') FROM DUAL;
SELECT TO_CHAR(TO_DATE('49/10/11'), 'YYYY/MM/DD') FROM DUAL;
SELECT TO_CHAR(TO_DATE('99/10/11'), 'RRRR/MM/DD') FROM DUAL;
SELECT TO_CHAR(TO_DATE('49/10/11'), 'RRRR/MM/DD') FROM DUAL;

-- 8. �� ������б� 2000�⵵ ���� �����ڵ��� �й��� A�� �����ϰ� �Ǿ��ִ�.
-- 2000�⵵ ���� �й��� ���� �л����� �й��� �̸��� �����ִ� SQL ������ �ۼ��Ͻÿ�.
SELECT * FROM TB_STUDENT;
SELECT STUDENT_NO, STUDENT_NAME FROM TB_STUDENT WHERE STUDENT_NO NOT LIKE 'A%';

-- 9. �й��� A517178 �� �ѾƸ� �л��� ���� �� ������ ���ϴ� SQL ���� �ۼ��Ͻÿ�.
-- ��, �̶� ��� ȭ���� ����� "����" �̶�� ������ �ϰ�, ������ �ݿø��Ͽ� �Ҽ��� ���� ���ڸ������� ǥ���Ѵ�.
SELECT ROUND(AVG(POINT),1) FROM TB_STUDENT
JOIN TB_GRADE USING(STUDENT_NO)
WHERE STUDENT_NO = 'A517178';

-- 10. �а��� �л����� ���Ͽ� "�а���ȣ", "�л���(��)" �� ���·� ����� ����� ������� ��µǵ��� �Ͻÿ�.
SELECT * FROM TB_DEPARTMENT;
SELECT DEPARTMENT_NO �а���ȣ, COUNT(DEPARTMENT_NO) "�л���(��)" FROM TB_STUDENT
GROUP BY DEPARTMENT_NO ORDER BY 1;

SELECT * FROM TB_STUDENT;

-- 11. ���� ������ �������� ���� �л��� ���� �� �� ���� �Ǵ��� �˾Ƴ��� SQL ���� �ۼ��Ͻÿ�.
SELECT * FROM TB_STUDENT;
SELECT COUNT(*) FROM TB_STUDENT
    WHERE COACH_PROFESSOR_NO IS NULL;
    
-- 12. �й��� A112113�� ���� �л��� �⵵ �� ������ ���ϴ� SQL ���� �ۼ��Ͻÿ�.
-- ��, �̶� ��� ȭ���� ����� "�⵵", "�⵵ �� ����"�̶�� ������ �ϰ�, ������ �ݿø��Ͽ� �Ҽ��� ���� �� �ڸ������� ǥ���Ѵ�.
SELECT * FROM TB_GRADE;
SELECT SUBSTR(TERM_NO,1,4) AS �⵵, ROUND(AVG(POINT),1) "�⵵ �� ����" FROM TB_GRADE WHERE STUDENT_NO = 'A112113'
    GROUP BY SUBSTR(TERM_NO,1,4) ORDER BY 1;
    
-- 13. �а� �� ���л� ���� �ľ��ϰ��� �Ѵ�.
-- �а� ��ȣ�� ���л� ���� ǥ���ϴ� SQL ������ �ۼ��Ͻÿ�.
SELECT * FROM TB_STUDENT;
SELECT * FROM TB_DEPARTMENT;

SELECT DEPARTMENT_NO �а��ڵ�� , SUM(DECODE(ABSENCE_YN, 'Y',1,'N',0)) "���л� ��" FROM TB_STUDENT 
    GROUP BY DEPARTMENT_NO ORDER BY 1;
    
    
-- 14. �� ���б��� �ٴϴ� �������� �л����� �̸��� ã���� �Ѵ�.
-- � sql ������ ����ϸ� �����ϰڴ°�?
SELECT * FROM TB_STUDENT;
SELECT STUDENT_NAME �����̸� , COUNT(*) "������ ��" FROM TB_STUDENT
WHERE STUDENT_NAME IN (
SELECT STUDENT_NAME FROM TB_STUDENT
GROUP BY STUDENT_NAME HAVING COUNT(STUDENT_NAME)>1
) GROUP BY STUDENT_NAME ORDER BY 1 ;

-- 15. �й��� A112113�� ���� �л��� �⵵, �б� �� ���� ����, �� ������ ���ϴ� SQL�� �ۼ�
-- (��, ������ �Ҽ��� 1�ڸ������� �ݿø��Ͽ� ǥ���Ѵ�.)

roll up 
SELECT * FROM TB_GRADE;
SELECT SUBSTR(TERM_NO,1,4) �⵵ ,SUBSTR(TERM_NO,5,2) �б�, ROUND(AVG(POINT) ,1) ����
    FROM TB_GRADE
    WHERE STUDENT_NO = 'A112113'
    GROUP BY ROLLUP(SUBSTR(TERM_NO,1,4),SUBSTR(TERM_NO,5,2))
    ORDER BY 1,2 ;