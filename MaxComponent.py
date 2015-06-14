def getGroupMember(X,groupList):
    for group in groupList:
        if X in group:
            return group
    return [X]
def getFrontier(group,relation):
    fron = []
    for node in group:
        for i in range(len(relation)):
            if relation[node][i] == '1' and relation[i][node] == '1':
                if not i in group:
                    fron.append(i)
    return fron
def MaxComponentOnce(groupListX,relation):
    for group in groupListX:
        fron = getFrontier(group,relation)
        for fronNumber in fron:
            member = getGroupMember(fronNumber,groupListX)
            group.extend(member)
            groupListX.remove(member)
            for i in member:
                if i in fron:
                    fron.remove(i)
    return groupListX
def listCopy(X):
    Y = []
    for i in X:
        Y.append(list(i))
    return Y
def MaxComponent(X,groupList,relation):
    groupListPre = listCopy(groupList)
    groupListN = MaxComponentOnce(groupList,relation)   
    while not groupListN == groupListPre:
        groupListTemp = listCopy(groupListN)
        groupListN = MaxComponentOnce(groupListN,relation)
        groupListPre = listCopy(groupListTemp)
    return len(getGroupMember(X,groupListN))
############################################
'''Testgroup = [[0,1,2],[3,4],[6],[8],[5],[7],[9]]
Testgroup = [[0],[1],[2],[3],[4],[6],[8],[5],[7],[9],[10],[11],[12],[13],[14],[15]]
Testgroup2=[[2, 0, 1, 3], [4], [7], [9, 8, 6, 5], [11, 10], [13, 12, 15], [14]]
Testgroup3 = [[13,12,15],[14]]
filenamex='relationMatrix.txt'
def getRelationMatrix(fileName):
        file = open(fileName,'r')
        list = []
        while True :
            Line = file.readline()
            if Line == '': break
            temp = Line.split('\n')
            temp = temp[0].split(' ')
            list.append(temp)              
        return list
relationX = getRelationMatrix(filenamex)
#print getGroupMember(1,Testgroup)
print MaxComponent(0,Testgroup,relationX)
print MaxComponent(1,Testgroup,relationX)
print MaxComponent(2,Testgroup,relationX)
print MaxComponent(3,Testgroup,relationX)
print MaxComponent(4,Testgroup,relationX)
print MaxComponent(5,Testgroup,relationX)
print MaxComponent(6,Testgroup,relationX)
print MaxComponent(7,Testgroup,relationX)
print MaxComponent(8,Testgroup,relationX)
print MaxComponent(9,Testgroup,relationX)
print MaxComponent(10,Testgroup,relationX)
print MaxComponent(11,Testgroup,relationX)
print MaxComponent(12,Testgroup,relationX)
print MaxComponent(13,Testgroup,relationX)
print MaxComponent(14,Testgroup,relationX)
print MaxComponent(15,Testgroup,relationX)
#print MaxComponentOnce(Testgroup2,relationX)
#print MaxComponentOnce(Testgroup3,relationX)'''