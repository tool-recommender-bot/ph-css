/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.css.supplementary.issues;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.css.ECSSVersion;
import com.helger.css.decl.CascadingStyleSheet;
import com.helger.css.handler.LoggingCSSParseExceptionCallback;
import com.helger.css.reader.CSSReader;
import com.helger.css.reader.CSSReaderSettings;
import com.helger.css.writer.CSSWriter;
import com.helger.css.writer.CSSWriterSettings;

/**
 * Test for issue 34: https://github.com/phax/ph-css/issues/34
 *
 * @author Philip Helger
 */
public final class Issue34Test
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (Issue34Test.class);

  @Test
  public void testIssue ()
  {
    final String css = ".pen {background-color: red;width:100px; height:100px} {* some incorrect block *} .pen {background-color: blue}";
    final CSSReaderSettings aSettings = new CSSReaderSettings ().setCSSVersion (ECSSVersion.LATEST)
                                                                .setBrowserCompliantMode (true)
                                                                .setCustomExceptionHandler (new LoggingCSSParseExceptionCallback ());
    final CascadingStyleSheet cascadingStyleSheet = CSSReader.readFromStringStream (css, aSettings);
    assertNotNull (cascadingStyleSheet);
    final CSSWriter writer = new CSSWriter (new CSSWriterSettings (ECSSVersion.LATEST, true));
    s_aLogger.info (writer.getCSSAsString (cascadingStyleSheet));
  }
}
