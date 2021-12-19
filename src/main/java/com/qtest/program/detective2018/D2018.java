package com.qtest.program.detective2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author skylynx
 */
public class D2018 {
    public static final int TESTCASE_NUMBERS = 10;
    String[] options = {"A", "B", "C", "D"};
    
    public static void main (String[] args) {
        D2018 d2018 = new D2018();
        List<String> answers = new ArrayList<>();
        d2018.iterate(answers);
    }

    private void iterate(List<String> answers) {
        if (answers.size() == TESTCASE_NUMBERS) {
            checkAnswer(answers);
            return;
        }
        for (String op : options) {
            answers.add(op);
            iterate(answers);
            answers.remove(answers.size()-1);
        }
    }

    private void checkAnswer(List<String> answers) {
        List<TestCase> testCases = initTestCases();
        for (TestCase testCase : testCases) {
            if (!testCase.singleSelect(answers)) {
                return;
            }
        }
        gotIt(answers);
    }

    private void gotIt(List<String> answers) {
        System.out.println(answers);
    }

    private List<TestCase> initTestCases() {
        List<TestCase> testCases = new ArrayList<>();
        testCases.add(genTestCase1());
        testCases.add(genTestCase2());
        testCases.add(genTestCase3());
        testCases.add(genTestCase4());
        testCases.add(genTestCase5());
        testCases.add(genTestCase6());
        testCases.add(genTestCase7());
        testCases.add(genTestCase8());
        testCases.add(genTestCase9());
        testCases.add(genTestCase10());
        return testCases;
    }

    private abstract static class TestCase {
        String answer;
        boolean opA, opB, opC, opD;

        /**
         * @param answers set of answers
         *
         */
        abstract void initOps(List<String> answers);
        boolean singleSelect(List<String> answers) {
            initOps(answers);
            return "A".equals(answer) && opA && !opB && !opC && !opD ||
                    "B".equals(answer) && !opA && opB && !opC && !opD ||
                    "C".equals(answer) && !opA && !opB && opC && !opD ||
                    "D".equals(answer) && !opA && !opB && !opC && opD;
        }
    }

    private TestCase genTestCase1() {
        return new TestCase() {
            @Override
            void initOps(List<String> answers) {
                answer = answers.get(0);
                opA = "A".equals(answers.get(0));
                opB = "B".equals(answers.get(0));
                opC = "C".equals(answers.get(0));
                opD = "D".equals(answers.get(0));
            }
        };
    }

    private TestCase genTestCase2() {
        return new TestCase() {
            @Override
            void initOps(List<String> answers) {
                answer = answers.get(1);
                opA = "C".equals(answers.get(4));
                opB = "D".equals(answers.get(4));
                opC = "A".equals(answers.get(4));
                opD = "B".equals(answers.get(4));
            }
        };
    }

    private TestCase genTestCase3() {
        return new TestCase() {
            @Override
            void initOps(List<String> answers) {
                answer = answers.get(2);
                opA = !answers.get(2).equals(answers.get(5)) &&
                        answers.get(5).equals(answers.get(1)) &&
                        answers.get(1).equals(answers.get(3));
                opB = !answers.get(2).equals(answers.get(5)) &&
                        answers.get(2).equals(answers.get(1)) &&
                        answers.get(1).equals(answers.get(3));
                opC = answers.get(2).equals(answers.get(5)) &&
                        !answers.get(5).equals(answers.get(1)) &&
                        answers.get(5).equals(answers.get(3));
                opD = answers.get(2).equals(answers.get(5)) &&
                        answers.get(5).equals(answers.get(1)) &&
                        !answers.get(1).equals(answers.get(3));
            }
        };
    }

    private TestCase genTestCase4() {
        return new TestCase() {
            @Override
            void initOps(List<String> answers) {
                answer = answers.get(3);
                opA = answers.get(0).equals(answers.get(4));
                opB = answers.get(1).equals(answers.get(6));
                opC = answers.get(0).equals(answers.get(8));
                opD = answers.get(5).equals(answers.get(9));
            }
        };
    }

    private TestCase genTestCase5() {
        return new TestCase() {
            @Override
            void initOps(List<String> answers) {
                answer = answers.get(4);
                opA = answers.get(4).equals(answers.get(7));
                opB = answers.get(4).equals(answers.get(3));
                opC = answers.get(4).equals(answers.get(8));
                opD = answers.get(4).equals(answers.get(6));
            }
        };
    }

    private TestCase genTestCase6() {
        return new TestCase() {
            @Override
            void initOps(List<String> answers) {
                answer = answers.get(5);
                opA = answers.get(1).equals(answers.get(7)) && answers.get(3).equals(answers.get(7));
                opB = answers.get(0).equals(answers.get(7)) && answers.get(5).equals(answers.get(7));
                opC = answers.get(2).equals(answers.get(7)) && answers.get(9).equals(answers.get(7));
                opD = answers.get(4).equals(answers.get(7)) && answers.get(8).equals(answers.get(7));
            }
        };
    }

    private TestCase genTestCase7() {
        return new TestCase() {
            @Override
            void initOps(List<String> answers) {
                String minOption = calcMinOption(answers);
                answer = answers.get(6);
                opA = "C".equals(minOption);
                opB = "B".equals(minOption);
                opC = "A".equals(minOption);
                opD = "D".equals(minOption);
            }
        };
    }

    private TestCase genTestCase8() {
        return new TestCase() {
            @Override
            void initOps(List<String> answers) {
                answer = answers.get(7);
                opA = isNoNeighbor(answers.get(0), answers.get(6));
                opB = isNoNeighbor(answers.get(0), answers.get(4));
                opC = isNoNeighbor(answers.get(0), answers.get(1));
                opD = isNoNeighbor(answers.get(0), answers.get(9));
            }
        };
    }

    private TestCase genTestCase9() {
        return new TestCase() {
            @Override
            void initOps(List<String> answers) {
                answer = answers.get(8);
                opA = answers.get(0).equals(answers.get(5)) != answers.get(5).equals(answers.get(4));
                opB = answers.get(0).equals(answers.get(5)) != answers.get(9).equals(answers.get(4));
                opC = answers.get(0).equals(answers.get(5)) != answers.get(1).equals(answers.get(4));
                opD = answers.get(0).equals(answers.get(5)) != answers.get(8).equals(answers.get(4));
            }
        };
    }

    private TestCase genTestCase10() {
        return new TestCase() {
            @Override
            void initOps(List<String> answers) {
                int maxDiff = calcMaxDiff(answers);
                answer = answers.get(9);
                opA = 3 == maxDiff;
                opB = 2 == maxDiff;
                opC = 4 == maxDiff;
                opD = 1 == maxDiff;
            }
        };
    }

    private List<Integer> calcCounts(List<String> answers) {
        int countA = 0, countB = 0, countC = 0, countD = 0;
        for (String an : answers) {
            switch (an) {
                case "A" -> countA++;
                case "B" -> countB++;
                case "C" -> countC++;
                case "D" -> countD++;
                default -> throw new IllegalStateException("Unexpected value: " + an);
            }
        }
        return Arrays.asList(countA, countB, countC, countD);
    }

    private String calcMinOption(List<String> answers) {
        List<Integer> counts = calcCounts(answers);
        int min = Collections.min(counts);
        if (min == counts.get(0)) {
            return "A";
        }
        if (min == counts.get(1)) {
            return "B";
        }
        if (min == counts.get(2)) {
            return "C";
        }
        return "D";
    }

    private int calcMaxDiff(List<String> answers) {
        List<Integer> counts = calcCounts(answers);
        int min = Collections.min(counts);
        int max = Collections.max(counts);
        return max-min;
    }

    private boolean isNoNeighbor(String answers1, String answers2) {
        return Math.abs(answers1.getBytes()[0] - answers2.getBytes()[0]) != 1;
    }
}
