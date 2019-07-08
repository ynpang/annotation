package com.enjoy.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.enjoy.annotation.SetValue;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class BeanUtil implements ApplicationContextAware {
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (this.applicationContext == null) {
			this.applicationContext = applicationContext;
			System.out.println("--------SpringUtil.applicationContext  ok ---------");
		}
	}

	public void setFieldValueForCollection(Collection col) {
		if (CollectionUtils.isEmpty(col)) {
			return;
		}

		// 1获得classz对象
		Class<?> clazz = col.iterator().next().getClass();

		// 2、获取里面的字段
		Field[] fields = clazz.getDeclaredFields();

		// 加个本地缓存
		Map<String, Object> cache = new HashMap<>();

		// 3、遍历处理带有指定注解的字段
		for (Field needSetField : fields) {
			// 3.1 获取字段的SetValue注解
			SetValue sv = needSetField.getAnnotation(SetValue.class);
			if (sv == null) {
				continue;
			}

			needSetField.setAccessible(true);

			// 3.2 获取注解的信息，准备好干活工具
			// 要调用的bean
			Object bean = this.applicationContext.getBean(sv.beanClass());
			try {
				// 要调用的方法
				Method method = sv.beanClass().getMethod(sv.method(),
						clazz.getDeclaredField(sv.paramField()).getType());
				// 入参字段
				Field paramField = clazz.getDeclaredField(sv.paramField());
				paramField.setAccessible(true);

				// 值来源字段
				Field targetField = null;
				boolean needInnerField = !StringUtils.isEmpty(sv.targetField());

				String keyPrefix = sv.beanClass().getName() + "-" + sv.method() + "-" + sv.targetField() + "-";
				// 4 遍历对象
				for (Object obj : col) {
					// 4.1 获取到参数值
					Object paramValue = paramField.get(obj);
					if (paramValue == null) {
						continue;
					}

					Object value = null;
					// 先重本方法缓存中取
					String key = keyPrefix + paramValue;
					if (cache.containsKey(key)) {
						value = cache.get(key);
					} else {
						// 4.2 调用bean的方法获得目标对象
						value = method.invoke(bean, paramValue);

						if (needInnerField) {
							if (value != null) {
								if (targetField == null) {
									targetField = value.getClass().getDeclaredField(sv.targetField());
									targetField.setAccessible(true);
								}
								value = targetField.get(value);
							}
						}
						// 放入cache
						cache.put(key, value);
					}
					// 4.3 设置值
					needSetField.set(obj, value);
				}
			} catch (NoSuchMethodException | SecurityException | NoSuchFieldException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
}
