package com.ohgiraffers.section01.xml;

import com.ohgiraffers.common.MenuDTO;
import com.ohgiraffers.common.SearchCriteria;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.ohgiraffers.common.Template.getSqlSession;

public class MenuService {
    public void selectMenuByPrice(int price) {
        SqlSession sqlSession = getSqlSession();
        DynamicSqlMapper dynamicSqlMapper = sqlSession.getMapper(DynamicSqlMapper.class);

        /* 기본 자료형의 변수명으로는 if문의 조건에서 값을 비교할 수 없다.
        * Map에 기본 자료형 값을 담아 전달하도록 한다.
        * (Map의 key 값, DTO 객체의 필드 값) */
        Map<String, Integer> map = new HashMap<>();
        map.put("price", price);
        List<MenuDTO> menuList = dynamicSqlMapper.selectMenuByPrice(map);

        if(menuList != null && !menuList.isEmpty()) {
            menuList.forEach(System.out::println);
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }

        sqlSession.close();

    }

    public void searchMenu(SearchCriteria searchCriteria) {
        SqlSession sqlSession = getSqlSession();
        DynamicSqlMapper dynamicSqlMapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menu = dynamicSqlMapper.searchMenu(searchCriteria);

        if(menu != null && !menu.isEmpty()) {
            menu.forEach(System.out::println);
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }

        sqlSession.close();
    }

    public void searchMenuBySupCategory(SearchCriteria searchCriteria) {
        SqlSession sqlSession = getSqlSession();
        DynamicSqlMapper dynamicSqlMapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menu = dynamicSqlMapper.searchMenuBySupCategory(searchCriteria);

        if(menu != null && !menu.isEmpty()) {
            menu.forEach(System.out::println);
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }

        sqlSession.close();
    }

    public void searchMenuByRandomMenuCode(Set<Integer> randomMenuCodeList) {
        SqlSession sqlSession = getSqlSession();
        DynamicSqlMapper dynamicSqlMapper = sqlSession.getMapper(DynamicSqlMapper.class);

        Map<String, Set<Integer>> criteria = new HashMap<>();
        criteria.put("randomMenuCode", randomMenuCodeList);
        List<MenuDTO> menu = dynamicSqlMapper.searchMenuByRandomMenuCode(criteria);

        if(menu != null && !menu.isEmpty()) {
            menu.forEach(System.out::println);
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }

        sqlSession.close();
    }

    public void searchMenuByNameOrCategory(Map<String, Object> criteria) {
        SqlSession sqlSession = getSqlSession();
        DynamicSqlMapper dynamicSqlMapper = sqlSession.getMapper(DynamicSqlMapper.class);


        List<MenuDTO> menu = dynamicSqlMapper.searchMenuByNameOrCategory(criteria);

        if(menu != null && !menu.isEmpty()) {
            menu.forEach(System.out::println);
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }

        sqlSession.close();
    }

    public void modifyMenu(Map<String, Object> criteria) {
        SqlSession sqlSession = getSqlSession();
        DynamicSqlMapper dynamicSqlMapper = sqlSession.getMapper(DynamicSqlMapper.class);

        int result = dynamicSqlMapper.updateMenu(criteria);

        if(result > 0) {
            sqlSession.commit();
            System.out.println("메뉴 정보 변경을 완료했습니다.");
        }else{
            sqlSession.rollback();
            System.out.println("메뉴 정보 변경을 실패했습니다.");
        }
        sqlSession.close();
    }
}
