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
    internal class MethodInvocationEmitter_ : InvocationEmitter_
    {
        public MethodInvocationEmitter_(CallInfo_ callInfo, DelegateCache cache)
            : base(callInfo, cache)
        {
        }

        protected override object Invoke(Delegate action)
        {
            if (CallInfo.IsStatic)
            {
                var invocation = (StaticMethodInvoker)action;
                return invocation.Invoke(CallInfo.Parameters);
            }
            else
            {
                var invocation = (MethodInvoker)action;
                return invocation.Invoke(CallInfo.Target, CallInfo.Parameters);
            }
        }

        protected override Delegate CreateDelegate()
        {
            MethodInfo methodInfo = GetMethodInfo();
            DynamicMethod method = CreateDynamicMethod();
            ILGenerator generator = method.GetILGenerator();
            LoadLocalsFromArguments(generator, CallInfo.IsStatic ? 0 : 1);
            if (!CallInfo.IsStatic)
            {
                LoadTarget(generator);
            }
            PushLocalsToStack(generator);
            generator.Emit(CallInfo.IsStatic ? OpCodes.Call : OpCodes.Callvirt, methodInfo);
            ReturnValue(generator, methodInfo.ReturnType);
            return method.CreateDelegate(CallInfo.IsStatic ? typeof(StaticMethodInvoker) : typeof(MethodInvoker));
        }

        protected MethodInfo GetMethodInfo()
        {
            var methodInfo = CallInfo.TargetType.GetMethod(CallInfo.Name,
                BindingFlags.ExactBinding | ScopeFlag | BindingFlags.Public | BindingFlags.NonPublic,
                null,
                CallingConventions.HasThis,
                CallInfo.ParamTypes,
                null);
            if (methodInfo == null)
            {
                throw new MissingMethodException(CallInfo.IsStatic ? "Static method " : "a " + CallInfo.Name + " does not exist");
            }
            return methodInfo;
        }

        protected DynamicMethod CreateDynamicMethod()
        {
            return CreateDynamicMethod("invoke",
                CallInfo.TargetType,
                Constants.ObjectType,
                CallInfo.IsStatic ? new[] { Constants.ObjectType.MakeArrayType() } : new[] { Constants.ObjectType, Constants.ObjectType.MakeArrayType() });
        }
    }
}
