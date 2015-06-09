import random

def extend(subgraphList,relationMatrix):
    for subgraph in subgraphList:
        idx = random.randint(0,len(relationMatrix))
        for i in range(idx,len(relationMatrix)):
            if subgraph.count == 0:
                subgraph.append(i)
            else:
                ok = 1
                for node in subgraph:
                    if relationMatrix[node][i]!= '1':
                        ok = 0
                        break
                if ok == 1:
                    subgraph.append(i)
        for i in range(0,idx):
            if subgraph.count == 0:
                subgraph.append(i)
            else:
                ok = 1
                for node in subgraph:
                    if relationMatrix[node][i]!= '1':
                        ok = 0
                        break
                if ok == 1:
                    subgraph.append(i)
    """             
    print 'extend'
    for subgraph in subgraphList:
        print subgraph  
    """
def repair(subgraphList,relationMatrix):
    for subgraph in subgraphList:
        idx = random.randint(0,len(relationMatrix))
        for i in range(idx,len(relationMatrix)):
            if i in subgraph:
                #0 delete 1 remain
                isDelete = random.randint(0,2)
                if isDelete == 1:
                    for j in range(i+1,len(relationMatrix)):
                        if j in subgraph:
                            
                            if relationMatrix[j][i] != '1':
                                subgraph.remove(j)
                    for j in range(0,i):
                        if j in subgraph:
                            
                            if relationMatrix[j][i] != '1':
                                subgraph.remove(j)          
                else: 
                    subgraph.remove(i)
        ###
        for i in range(idx-1,-1,-1):
            if i in subgraph:
                #0 delete 1 remain
                isDelete = random.randint(0,2)
                if isDelete == 1:
                    for j in range(i-1,-1,-1):
                        if j in subgraph:
                            if relationMatrix[j][i] != '1':
                                subgraph.remove(j)
                else: 
                    subgraph.remove(i)
    """
    print 'repair' 
    for subgraph in subgraphList:
        print subgraph  
    """

def enLarge(subgraphList,relationMatrix):
    for subgraph in subgraphList:
        s = random.randint(0,len(relationMatrix)-len(subgraph))
        randomNodes = random.sample(range(len(relationMatrix)),s)
        for node in randomNodes:
            if node not in subgraph:
                subgraph.append(node)
    
    """
    print 'enlarge'
    for subgraph in subgraphList:
        print subgraph  
    """
def mutation(afterCross):
    for i in range(len(afterCross)):
        s = random.randint(0,10)
        if s == 0:
            afterCross[i] = 1 - afterCross[i]
    return afterCross
def uniformCrossOver(listA,listB,relationMatrix):
    presentA = []
    presentB = []
    for i in range(len(relationMatrix)):
        presentA.append(0)
        presentB.append(0)
    
    for i in range (len(listA)):
        presentA[listA[i]] = 1
        
    for i in range (len(listB)):
        presentB[listB[i]] = 1


    afterCross =[]
    for i in range(len(presentA)):
        which = random.randint(0,2)
        if which == 0:
            afterCross.append(presentA[i])
        else:
            afterCross.append(presentB[i])
            
    return  afterCross
        
def crossOverAndMutation(subgraphList,relationMatrix):
    afterList = []
    returnList = []
    for i in range(len(subgraphList)/2):
        afterCross = uniformCrossOver(subgraphList[2*i],subgraphList[2*i+1],relationMatrix)
        afterCross = mutation(afterCross)
        afterList.append(afterCross)
    for list in afterList:
        s = []
        for i in range(len(list)):
            if list[i] == 1:
                s.append(i)
        returnList.append(s)
        
    return returnList
        
def startFind(subgraphList,relationMatrix):
        t = 1
        last = subgraphList
        now = []
        while t < 100:
            t = t + 1
            
            now = crossOverAndMutation(subgraphList,relationMatrix)
            
            enLarge(now,relationMatrix)
            repair(now,relationMatrix)
            extend(now,relationMatrix)
            
            length = 2
            if len(last) < length:
                length = len(last)
            for i in range(length):
                now.append(last[i])
            now = sorted(now,key = lambda x:len(x),reverse=True)
            last = now
        
        return now  
    
def findClique(relationMatrix):
    howManySubgraph = 10
    subgraphList = []
    for sub in range(howManySubgraph):
        s = random.randint(1,len(relationMatrix)-1)
        subgraphIdList = random.sample(range(len(relationMatrix)),s)
        subgraphList.append(subgraphIdList) 
    enLarge(subgraphList,relationMatrix)
    repair(subgraphList,relationMatrix)
    extend(subgraphList,relationMatrix)
    subgraphList = sorted(subgraphList,key = lambda x:len(x),reverse=True)  
    #print subgraphList
    
    s = startFind(subgraphList,relationMatrix)
    
    return s
def howManyPeopleLikeThem(list,relationMatrix):
    temp = 0
    for ID in list:
        for otherID in range(len(relationMatrix)):
            if otherID not in list:
                if relationMatrix[otherID][ID] == '1':
                    temp = temp + 1
    return temp
    
def checkDoubleLink(relationMatrix):
    for i in range(len(relationMatrix)):
        for j in range(len(relationMatrix)):
            if relationMatrix[i][j] != '1' or relationMatrix[j][i] !='1':
                relationMatrix[i][j] = '0'
    return relationMatrix
    
class person:
    relationMatrix = []
    ID = -1
    def __init__(self,fileName,ID):
        self.relationMatrix = self.getRelationMatrix(fileName)
        self.ID = ID
        
    def getRelationMatrix(self,fileName):
        file = open(fileName,'r')
        list = []
        while True :
            Line = file.readline()
            if Line == '': break
            temp = Line.split('\n')
            temp = temp[0].split(' ')
            list.append(temp)
        return list
    
    def WhoYouDontLike(self,list):
        for ID in list:
            self.relationMatrix[self.ID][ID] = '-1'


    
PersonList = []
no1 = person('relationMatrix.txt',0)
no2 = person('relationMatrix.txt',1)










s = checkDoubleLink(no1.relationMatrix)



clique = []
while(True):
    Maxclique = findClique(s)
    if Maxclique == None:
        break
    if len(Maxclique[0]) == 1:
        break
    print Maxclique
    clique.append(Maxclique[0])
    for list in s:
        for node in Maxclique[0]:
            list[node] = '0'
    s = checkDoubleLink(s)
    
print 'ssss',   clique

whoInClique = []
for i in range(len(s)):
    whoInClique.append(0)
for list in clique:
    for node in list :
        whoInClique[node] = 1
print whoInClique
for i in range(len(whoInClique)):
    if whoInClique[i] == 0:
        clique.append([i])
print clique
everyCliqueLovedByHowManyPeople = []


clique = sorted(clique,key = lambda x:(len(x),howManyPeopleLikeThem(x,no2.relationMatrix)),reverse=True)
print clique

f2 = open('cliqueFile.txt','w')
for list in clique:
    for no in list :
        f2.write(str(no) + ' ')
    f2.write('\n')    
"""
#no1.WhoYouDontLike([0,1,2])
PersonList.append(no1)
no2 = person('relationMatrix.txt',1)
#no2.WhoYouDontLike([0])
PersonList.append(no2)
no3 = person('relationMatrix.txt',2)
#no3.WhoYouDontLike([1])
PersonList.append(no3)
relationArray = []


howManyPeopleLikeHim = []
for p in PersonList:
    relationArray.append(p.relationMatrix[p.ID])
print relationArray
for ID in range(len(relationArray)):
    count = 0
    for otherID in range(len(relationArray)):
        if otherID == ID: continue
        if relationArray[otherID][ID] == '1': count = count + 1
    howManyPeopleLikeHim.append(count)
print howManyPeopleLikeHim
"""


