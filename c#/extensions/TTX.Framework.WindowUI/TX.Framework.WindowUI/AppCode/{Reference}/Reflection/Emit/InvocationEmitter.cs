#region COPYRIGHT
//
//     THIS IS GENERATED BY TEMPLATE
//     
//     AUTHOR  :     ROYE
//     DATE       :     2010
//
//     COPYRIGHT (C) 2010, TIANXIAHOTEL TECHNOLOGIES CO., LTD. ALL RIGHTS RESERVED.
//
#endregion

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace System.Reflection.Emit
{
    internal abstract class InvocationEmitter : BaseEmitter
    {
        protected InvocationEmitter(CallInfo callInfo)
            : base(callInfo)
        {
        }

        protected byte CreateLocalsForByRefParams(byte paramArrayIndex, MethodBase invocationInfo)
        {
            byte numberOfByRefParams = 0;
            var parameters = invocationInfo.GetParameters();
            for (int i = 0; i < CallInfo.ParamTypes.Length; i++)
            {
                Type paramType = CallInfo.ParamTypes[i];
                if (paramType.IsByRef)
                {
                    Type type = paramType.GetElementType();
                    Generator.DeclareLocal(type);
                    if (!parameters[i].IsOut) // no initialization necessary is 'out' parameter
                    {
                        Generator.ldarg(paramArrayIndex)
                            .ldc_i4(i)
                            .ldelem_ref
                            .CastFromObject(type)
                            .stloc(numberOfByRefParams)
                            .end();
                    }
                    numberOfByRefParams++;
                }
            }
            return numberOfByRefParams;
        }

        protected void AssignByRefParamsToArray(int paramArrayIndex)
        {
            byte currentByRefParam = 0;
            for (int i = 0; i < CallInfo.ParamTypes.Length; i++)
            {
                Type paramType = CallInfo.ParamTypes[i];
                if (paramType.IsByRef)
                {
                    Generator.ldarg(paramArrayIndex)
                        .ldc_i4(i)
                        .ldloc(currentByRefParam++)
                        .boxIfValueType(paramType.GetElementType())
                        .stelem_ref
                        .end();
                }
            }
        }

        protected void PushParamsOrLocalsToStack(int paramArrayIndex)
        {
            byte currentByRefParam = 0;
            for (int i = 0; i < CallInfo.ParamTypes.Length; i++)
            {
                Type paramType = CallInfo.ParamTypes[i];
                if (paramType.IsByRef)
                {
                    Generator.ldloca_s(currentByRefParam++);
                }
                else
                {
                    Generator.ldarg(paramArrayIndex)
                        .ldc_i4(i)
                        .ldelem_ref
                        .CastFromObject(paramType);
                }
            }
        }
    }
}
