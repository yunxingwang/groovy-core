/*
 * Copyright 2003-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codehaus.groovy.tools.stubgenerator

/**
 * Test that array covariant return types are compiled successfully.
 *
 * @author Paul King
 */
class Groovy6617Bug extends StringSourcesStubTestCase {

    Map<String, String> provideSources() {
        [
                'foo/JavaApi.java'            : '''
                    package foo;
                    import java.util.List;
                    public interface JavaApi {
                        public foo.JavaDataObject[] makeArray ();
                        public List<JavaDataObject> makeList ();
                    }
            ''',
                'foo/JavaDataObject.java'     : '''
                    package foo;
                    public class JavaDataObject {
                        boolean active;
                    }
            ''',
                'bar/GroovyLangService.groovy': '''
                    package bar

                    import foo.JavaApi
                    import foo.JavaDataObject

                    class GroovyLangService implements JavaApi {
                        JavaDataObject[] makeArray () {
                            new JavaDataObject[10]
                        }
                        List<JavaDataObject> makeList () {
                            new ArrayList<JavaDataObject>(10)
                        }
                    }
            '''
        ]
    }

    void verifyStubs() { }
}
