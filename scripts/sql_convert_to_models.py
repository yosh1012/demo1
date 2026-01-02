"""
generate model files by SQL statements
"""

import re # regex
import sys # for standard input

TYPE_MAP = {
    'BIGSERIAL': 'Long', 'BIGINT': 'Long', 'INT': 'Int', 'BOOLEAN': 'Boolean',
    'TEXT': 'String', 'TIMESTAMP': 'LocalDateTime', 'DATE': 'LocalDate', 'DECIMAL': 'BigDecimal'
}

def main():
    print("paste SQL here: ")
    sql = sys.stdin.read()
        
    model = convertSqlToScalaModel(sql)
    tableModel = convertModelToTableModel(model)
    columnList = convertSqlToColumnList(sql)
    
    print("### Record Rlass ###")
    print(model)
    print("### Table Class ###")
    print(tableModel)
    print("### Column List")
    print(columnList)
    
def convertSqlToScalaModel(sql):
    result = sql
    
    result = re.sub(
        r'\n[ \t]*\n',
        r'\n',
        result
    )
    
    result = re.sub(
        r'^[ \t]+(\w+)[ \t]+(\w+(?:\([0-9]+\))?)[ \t]*,?[ \t]*(NOT NULL)?([^\n]*)',
        r'\t\1: \2, //\3 \4',
        result,
        flags = re.MULTILINE
    )
    
    result = re.sub(
        r'^(\t\w+:[ \t])(\w+(?:\([0-9]+\))?)(,[ \t]+//)(?![ \t]*NOT NULL|[ \t]*PRIMARY)',
        r'\1Option[\2]\3',
        result,
        flags = re.MULTILINE
    )
    
    result = re.sub(
        r'VARCHAR\([0-9]+\)',
        r'String',
        result,
        flags = re.IGNORECASE
    )
    
    result = re.sub(
        r'CURRENT_TIMESTAMP',
        r'CURRENT_TIMESTAMP',
        result
    )
    
    for sql_type, scala_type in TYPE_MAP.items():
        result = re.sub(
            r'\b' + sql_type + r'\b',
            scala_type,
            result
            )
    
    result = re.sub(
        r'NOT NULL[ \t]*',
        r'',
        result
    )
    
    result = re.sub(
        r'//[ \t]+',
        r'// ',
        result
    )
    
    result = re.sub(
        r'//[ \t]*$',
        r'',
        result,
        flags = re.MULTILINE
    )
    
    result = re.sub(
        r',[ \t]*$',
        r',',
        result,
        flags = re.MULTILINE
    )
            
    return result

def convertModelToTableModel(model):
    result = model
    
    result = re.sub(
        r'^[ \t]*(\w+):[ \t]*([^,/]+),?.*',
        r'\tdef \1 = column[\2]("\1")',
        result,
        flags = re.MULTILINE
    )
    return result

def convertSqlToColumnList(sql):
    result = sql
    
    result = re.sub(
        r'\n[ \t]*\n',
        r'\n',
        result
    )
    
    result = re.sub(
        r'^[ \t]+(\w+)[ \t]+[^\n]*',
        r'\t\t\1,',
        result,
        flags = re.MULTILINE
    )
    
    lines = result.split('\n')
    lines = [line for line in lines if line.startswith('\t')]
    
    return '\n'.join(lines)

main()