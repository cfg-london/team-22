#!/usr/bin/python
import sys

def main(argv):
    t = open('input.txt', 'r')
    text = t.read()
    textList = text.strip().split("\n")
    textList = list(filter(None, textList))
    #print(textList)
    for i in range(0,len(textList),2):
        print('<div class="question">',textList[i],'\n<div class="answer>">',textList[i+1],'</div></div>\n')
    
if __name__ == "__main__":
   main(sys.argv[1])
