package com.helper;

import com.domain.Person;

import java.io.InputStream;
import java.util.List;

public interface XmlHelper {
    List<Person> parsXml(InputStream stream);
}
