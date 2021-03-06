/*
 * The MIT License
 *
 * Copyright (c) 2010, Yahoo! Inc. and contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jvnet.hudson.plugins.collapsingconsolesections;

import hudson.Extension;
import hudson.console.ConsoleAnnotator;
import hudson.console.ConsoleAnnotatorFactory;
import jenkins.model.Jenkins;

/**
 *
 * @author dty
 */
@Extension(ordinal = -500) // Act after timestamper plugins and others.
public class CollapsingSectionAnnotatorFactory extends ConsoleAnnotatorFactory {
    @Override
    public ConsoleAnnotator newInstance(Object context) {
        final Jenkins jenkins = Jenkins.getActiveInstance();

        CollapsingSectionNote.DescriptorImpl descr = jenkins.getDescriptorByType(CollapsingSectionNote.DescriptorImpl.class);
        if (descr.getSectionDefinitions().length == 0) {
            return null;
        }

        return new CollapsingSectionAnnotator(descr.getConfiguration());
    }
}
