package com.ohgiraffers.section02.provider;

import com.ohgiraffers.common.MenuDTO;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;

public interface SqlBuilderMapper {

    @InsertProvider(type = SqlBuilderProvider.class, method = "insertMenu")
    int insertMenu(MenuDTO menuDTO);

    /* 전달 파라미터가 유효한 값(문자열의 경우 빈 문자열이 아니여야 하고 숫자의 경우 0이상)을 가질
    * 경우에만 수정에 반영하는 동적 쿼리 구현 */
    @UpdateProvider(type = SqlBuilderProvider.class, method = "updateMenu")
    int updateMenu(MenuDTO menuDTO);

    /* 기본 자료형 값을 전달하는 경우 @Param 어노테이션을 이용해야 한다.
    * 또는 전달 값이 2개 이상인 경우에도 @Param 어노테이션을 사용해야 한다.
    * 단,  Provider 메소드의 매개변수 선언부는 없어야 한다. */
    @DeleteProvider(type = SqlBuilderProvider.class, method = "deleteMenu")
    int deleteMenu(@Param("menuCode")int menuCode);
}
