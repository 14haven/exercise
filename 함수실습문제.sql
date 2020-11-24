SELECT * FROM EMPLOYEE;

-- 1. ������� �ֹι�ȣ�� ��ȸ�� ��, �ֹι�ȣ 9��° �ڸ����� �������� '*'���ڷ� ä��
SELECT EMP_NAME, RPAD(SUBSTR(EMP_NO,1,8),14,'*')AS �ֹι�ȣ FROM EMPLOYEE;

-- 2. ������, �����ڵ�, ����(��) ��ȸ ��, ������ �� 57,000,000���� ǥ�õǰ� ��
-- ������ ���ʽ�����Ʈ�� ����� 1��ġ �޿���
SELECT EMP_NAME, JOB_CODE, TO_CHAR((SALARY*12)*NVL(1+BONUS,1),'L999,999,999') AS ���� FROM EMPLOYEE;

-- 3. �μ��ڵ尡 D5, D9�� ������ �߿��� 2004�⵵�� �Ի��� ������ �� ��ȸ��
SELECT * FROM EMPLOYEE;
DESC EMPLOYEE;

SELECT EMP_ID, EMP_NAME, DEPT_CODE, HIRE_DATE FROM EMPLOYEE 
    WHERE  DEPT_CODE IN('D5','D9') AND EXTRACT(YEAR FROM HIRE_DATE)=2004;

-- 4. ������, �Ի���, �Ի��� ���� �ٹ��ϼ� ��ȸ ��, �ָ��� ������
SELECT EMP_NAME, HIRE_DATE, LAST_DAY(HIRE_DATE)-HIRE_DATE �ٹ��ϼ� FROM EMPLOYEE;

-- 5. ������, �μ��ڵ�, �������, ����(��) ��ȸ
-- ��, ��������� �ֹι�ȣ���� �����ؼ�, 00��, 00��, 00�Ϸ� ��µǰ� ��
-- ���̴� �ֹι�ȣ���� �����ؼ� ��¥�����ͷ� ��ȯ�� ����, �����

SELECT EMP_NAME, DEPT_CODE, SUBSTR(EMP_NO,1,2) || '��' AS ���� , SUBSTR(EMP_NO,3,2) || '��' AS �� ,SUBSTR(EMP_NO,5,2) || '��' AS ��
    FROM EMPLOYEE;

SELECT * FROM EMPLOYEE;
DESC EMPLOYEE;

SELECT REPLACE(EMP_NO,SUBSTR(EMP_NO,1,2),1900+SUBSTR(EMP_NO,1,2)) FROM EMPLOYEE;
SELECT SUBSTR(REPLACE(EMP_NO,SUBSTR(EMP_NO,1,2),1900+SUBSTR(EMP_NO,1,2)),1,8) FROM EMPLOYEE;
SELECT TO_CHAR(TO_DATE('SUBSTR(EMP_NO,1,6)'),'YYYY-MM-DD') FROM EMPLOYEE;

SELECT TO_DATE('19941111') FROM DUAL;


-- 6. �������� �Ի��Ϸ� ���� �⵵�� ������, �� �⵵�� �Ի��ο����� ���Ͻÿ�.
-- �Ʒ��� �⵵�� �Ի��� �ο����� ��ȸ�Ͻÿ�.
-- -> to_char, decode, sum ���

SELECT COUNT(*) ��ü������ , SUM(DECODE(TO_CHAR(HIRE_DATE,'YYYY'), 2001, 1, 0)) AS "2001��", 
    SUM(DECODE(TO_CHAR(HIRE_DATE,'YYYY'), 2002, 1, 0)) AS "2002��",
    SUM(DECODE(TO_CHAR(HIRE_DATE,'YYYY'), 2003, 1, 0)) AS "2003��",
    SUM(DECODE(TO_CHAR(HIRE_DATE,'YYYY'), 2004, 1, 0)) AS "2004��"
    FROM EMPLOYEE;
    

-- 7. �μ��ڵ尡 D5�̸� �ѹ���, D6�̸� ��ȹ��, D9�̸� �����η� ó���Ͻÿ�.
-- ��, �μ��ڵ尡 D5, D6, D9�� ������ ������ ��ȸ��
=> case ���
-- �μ��ڵ� ���� �������� ������


SELECT DEPT_CODE, CASE WHEN DEPT_CODE = 'D5' THEN '�ѹ���'
    WHEN DEPT_CODE = 'D6' THEN '��ȹ��'
    WHEN DEPT_CODE = 'D9' THEN '������'
    END AS �μ���
    FROM EMPLOYEE WHERE DEPT_CODE IN('D5','D6','D9') ORDER BY DEPT_CODE;
    DEPT_CODE = 'D5' OR DEPT_CODE ='D6' OR DEPT_CODE ='D9' ORDER BY DEPT_CODE;


